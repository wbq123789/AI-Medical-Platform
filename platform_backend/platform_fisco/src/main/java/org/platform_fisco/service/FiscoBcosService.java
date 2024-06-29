package org.platform_fisco.service;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.abi.ABICodec;
import org.fisco.bcos.sdk.abi.ABICodecException;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.*;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.platform_fisco.utils.JsonUtil;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

/**
 * @program: fisco_backend
 * @description: webase服务
 * @author: 王贝强
 * @create: 2024-04-26 14:14
 */
@Service
public class FiscoBcosService {
    public final String configFile ="src/main/resources/config.toml";

    public String md5Encrypt(long timestamp, String appKey, String appSecret) {
        try {
            String dataStr = timestamp + appKey + appSecret;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes(StandardCharsets.UTF_8));
            byte[] s = m.digest();
            StringBuilder result = new StringBuilder();
            for (byte b : s) {
                result.append(Integer.toHexString((0x000000FF & b) | 0xFFFFFF00).substring(6));
            }
            return result.toString().toUpperCase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getTotalTransactionCount(int Group_id){
        BcosSDK sdk = BcosSDK.build(configFile);
        Client client = sdk.getClient(Group_id);
        TotalTransactionCount transactionCount = client.getTotalTransactionCount();
        String blockNumber = transactionCount.getTotalTransactionCount().getBlockNumber();
        int block = Integer.getInteger(blockNumber,16);
        String txSum = transactionCount.getTotalTransactionCount().getTxSum();
        int tx = Integer.getInteger(txSum,16);
//        return block+","+tx;
        return blockNumber+","+txSum;
    }

    public String getBatchReceiptsByBlockNumberAndRange(int Group_id, int BlockNumber){
        BcosSDK sdk = BcosSDK.build(configFile);
        Client client=sdk.getClient(Group_id);
        // 获取最新区块高度的所有交易回执信息(cient初始化过程省略，详细可以参考快速入门)
        BcosTransactionReceiptsDecoder bcosTransactionReceiptsDecoder = client.getBatchReceiptsByBlockNumberAndRange(
                        BigInteger.valueOf(BlockNumber), "0", "-1");
        // 解码交易回执信息
        BcosTransactionReceiptsInfo.TransactionReceiptsInfo receiptsInfo = bcosTransactionReceiptsDecoder.decodeTransactionReceiptsInfo();
        // 获取回执所在的区块信息
        BcosTransactionReceiptsInfo.BlockInfo blockInfo = receiptsInfo.getBlockInfo();
        System.out.println(blockInfo.getBlockHash());
        return "";
    }
    public void getTransactionByHash(int Group_Id,String hash) {
        BcosSDK sdk = BcosSDK.build(configFile);
        Client client = sdk.getClient(Group_Id);
        BcosTransaction transaction = client.getTransactionByHash(hash);
        transaction.getTransaction().ifPresent(jsonTransactionResponse -> {
            ABICodec abiCodec=new ABICodec(new CryptoSuite(1));
            try {
                List<String> input = abiCodec.decodeTransactionInputToString("[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"result\",\"type\":\"int256\"},{\"indexed\":false,\"internalType\":\"string\",\"name\":\"file_id\",\"type\":\"string\"},{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"round\",\"type\":\"int256\"}],\"name\":\"FileInserted\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"result\",\"type\":\"int256\"},{\"indexed\":false,\"internalType\":\"string\",\"name\":\"file_id\",\"type\":\"string\"},{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"round\",\"type\":\"int256\"}],\"name\":\"FileRemoved\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"result\",\"type\":\"int256\"},{\"indexed\":false,\"internalType\":\"string\",\"name\":\"file_id\",\"type\":\"string\"},{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"round\",\"type\":\"int256\"},{\"indexed\":false,\"internalType\":\"int256\",\"name\":\"new_reward\",\"type\":\"int256\"}],\"name\":\"RewardUpdated\",\"type\":\"event\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_file_id\",\"type\":\"string\"},{\"internalType\":\"string\",\"name\":\"_file_content\",\"type\":\"string\"},{\"internalType\":\"int256\",\"name\":\"_round\",\"type\":\"int256\"},{\"internalType\":\"int256\",\"name\":\"_reward\",\"type\":\"int256\"}],\"name\":\"insert\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_file_id\",\"type\":\"string\"},{\"internalType\":\"int256\",\"name\":\"_round\",\"type\":\"int256\"}],\"name\":\"remove\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_file_id\",\"type\":\"string\"}],\"name\":\"selectById\",\"outputs\":[{\"internalType\":\"int256[]\",\"name\":\"\",\"type\":\"int256[]\"},{\"internalType\":\"int256[]\",\"name\":\"\",\"type\":\"int256[]\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"int256\",\"name\":\"_round\",\"type\":\"int256\"},{\"internalType\":\"int256\",\"name\":\"_reward\",\"type\":\"int256\"}],\"name\":\"selectByRoundAndReward\",\"outputs\":[{\"internalType\":\"int256[]\",\"name\":\"\",\"type\":\"int256[]\"},{\"internalType\":\"int256[]\",\"name\":\"\",\"type\":\"int256[]\"},{\"internalType\":\"string[]\",\"name\":\"\",\"type\":\"string[]\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"_file_id\",\"type\":\"string\"},{\"internalType\":\"int256\",\"name\":\"_round\",\"type\":\"int256\"},{\"internalType\":\"int256\",\"name\":\"_new_reward\",\"type\":\"int256\"}],\"name\":\"updateReward\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"
                        ,jsonTransactionResponse.getInput());
                input.forEach(System.out::println);
            } catch (ABICodecException e) {
                throw new RuntimeException(e);
            }
        });
    }
    public void getBlockByHash(int Group_Id,String hash,boolean includeTransactions) {
        BcosSDK sdk = BcosSDK.build(configFile);
        Client client = sdk.getClient(Group_Id);
        BcosBlock block = client.getBlockByHash(hash, includeTransactions);
        System.out.println(block.getBlock().getTimestamp());
        List<BcosBlock.TransactionResult> transactions = block.getBlock().getTransactions();
        transactions.forEach(transactionResult -> {
            String json = JsonUtil.toJson(transactionResult);
            System.out.println(json);
        });

    }
}
