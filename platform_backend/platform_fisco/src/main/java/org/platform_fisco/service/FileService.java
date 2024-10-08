package org.platform_fisco.service;

import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.client.protocol.response.TotalTransactionCount;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.platform_fisco.entity.transaction;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class FileService {
    private final String[] address= {
            "0x9aa88243edfb8f80ae4fb79d5ff56b7fb9122ba9",
            "0xe05f71e8d7215717f0763e37aad89d71d313c2c4",
            "0x424b77d344920244013e29ff6cf0f348985fb09f",
            "0xb030f5e03bfb1513c0a6f38994ae94cf313e3adc",
            "0x5ed14cf0c41d4bc45174f22c59d8be681106787d"
    };
    public final String configFile ="platform_backend/platform_fisco/src/main/resources/config.toml";
    BcosSDK sdk;
    List<Client> clients;
    List<AssembleTransactionProcessor> processors;
    {
        clients=new ArrayList<>();
        sdk = BcosSDK.build(configFile);
        for (int i=1;i<6;i++){
            clients.add(sdk.getClient(i));
        }
        processors=new ArrayList<>();
        for (int i=1;i<6;i++){
            CryptoKeyPair keyPair = clients.get(i-1).getCryptoSuite().createKeyPair();
            AssembleTransactionProcessor transactionProcessor;
            try {
                transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(clients.get(i-1), keyPair, "platform_backend/platform_fisco/src/main/resources/abi/", "platform_backend/platform_fisco/src/main/resources/bin/");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processors.add(transactionProcessor);
        }
    }

    public boolean insertFile(int Group_id,String Agency_id,String File_id,String File_param,String Content,int Round,int Reward) throws Exception {
        //通过processor获取预先创建好的连接
        AssembleTransactionProcessor transactionProcessor = processors.get(Group_id-1);

        // 创建调用交易函数的参数，此处为传入一个参数
        List<Object> params = new ArrayList<>();
        String MethodName="insert";
        params.add(Agency_id);
        params.add(File_id);
        params.add(File_param);
        params.add(Content);
        params.add(Round);
        params.add(Reward);
        Date date=new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        params.add(dateFormat.format(date));

        // 调用HelloWorld合约，合约地址为helloWorldAddress， 调用函数名为『set』，函数参数类型为params
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader("File", address[Group_id-1], MethodName, params);
        return Objects.equals(transactionResponse.getValues(), "1");
    }
    public boolean insertFile(String Agency_id,String File_id,String File_param,String Content,int Round,int Reward) throws Exception {
        //通过processor获取预先创建好的连接(Agency1)
        AssembleTransactionProcessor transactionProcessor = processors.get(0);

        // 创建调用交易函数的参数，此处为传入一个参数
        List<Object> params = new ArrayList<>();
        String MethodName="insert";
        params.add(Agency_id);
        params.add(File_id);
        params.add(File_param);
        params.add(Content);
        params.add(Round);
        params.add(Reward);
        Date date=new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        params.add(dateFormat.format(date));
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader("File", address[0], MethodName, params);
        return Objects.equals(transactionResponse.getValues(), "1");
    }
    public boolean updateReward(int Group_id,String Agency_id,String File_id,int Round,int newReward) throws Exception {
        //通过processor获取预先创建好的连接
        AssembleTransactionProcessor transactionProcessor = processors.get(Group_id-1);

        // 创建调用交易函数的参数，此处为传入一个参数
        List<Object> params = new ArrayList<>();
        String MethodName="updateReward";
        params.add(Agency_id);
        params.add(File_id);
        params.add(Round);
        params.add(newReward);
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader("File", address[Group_id-1], MethodName, params);
        return Objects.equals(transactionResponse.getValues(), "1");
    }
    public List<transaction> selectById(int Group_id, String agency_id) throws Exception {
        //通过processor获取预先创建好的连接
        AssembleTransactionProcessor transactionProcessor = processors.get(Group_id-1);

        // 创建调用交易函数的参数，此处为传入一个参数
        List<Object> params = new ArrayList<>();
        String MethodName="selectById";
        params.add(agency_id);
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader("File", address[Group_id-1],MethodName, params);
        List<Object> object = transactionResponse.getReturnObject();
        List<transaction> transactions = new ArrayList<>();
        List<Object> id = List.of();
        List<Object> file_id= List.of();
        List<Object> file_param= List.of();
        List<Object> round= List.of();
        List<Object> reward= List.of();
        List<Object> time= List.of();
        if (object==null)
            return transactions;
        for (int i = 0; i <object.size();i++) {
             ArrayList<Object> array= (ArrayList<Object>) object.get(i);
             if (!array.isEmpty()){
                 switch (i){
                     case 0: id= (List<Object>) array.clone();
                     case 1: file_id= (List<Object>) array.clone();
                     case 2: file_param= (List<Object>) array.clone();
                     case 3: round= (List<Object>) array.clone();
                     case 4: reward= (List<Object>) array.clone();
                     case 5: time= (List<Object>) array.clone();
                 }
             }
        }
        for (int i = 0; i < id.size(); i++) {
            transactions.add(new transaction()
                    .setAgency_id((String) id.get(i))
                    .setFile_id(file_id.get(i).toString())
                    .setFile_param(file_param.get(i).toString())
                    .setRound(round.get(i).toString())
                    .setReward(reward.get(i).toString())
                    .setTime((String) time.get(i))
            );
        }
        return transactions;
    }
    public ArrayList<Object> selectById_01(int Group_id,String agency_id) throws Exception {
        //通过processor获取预先创建好的连接
        AssembleTransactionProcessor transactionProcessor = processors.get(Group_id-1);

        // 创建调用交易函数的参数，此处为传入一个参数
        List<Object> params = new ArrayList<>();
        String MethodName="selectById";
        params.add(agency_id);
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader("File", address[Group_id-1],MethodName, params);
        List<Object> object = transactionResponse.getReturnObject();
        if (object==null)
            return new ArrayList<>();
        else
            return (ArrayList<Object>) object.get(0);
    }
    public Object selectByIdAndRound(int Group_id,String agency_id,String File_id,int Round) throws Exception {
        //通过processor获取预先创建好的连接
        AssembleTransactionProcessor transactionProcessor = processors.get(Group_id-1);

        // 创建调用交易函数的参数，此处为传入一个参数
        List<Object> params = new ArrayList<>();
        String MethodName="selectByIdAndRound";
        params.add(agency_id);
        params.add(File_id);
        params.add(Round);
        TransactionResponse transactionResponse = transactionProcessor.sendTransactionAndGetResponseByContractLoader("File", address[Group_id-1], MethodName, params);
        List<Object> object = transactionResponse.getReturnObject();
        return object.get(4);
    }
    public String getTotalTransactionCount(int Group_id){
        TotalTransactionCount transactionCount = clients.get(Group_id-1).getTotalTransactionCount();
        String blockNumber = transactionCount.getTotalTransactionCount().getBlockNumber();
        int block = Integer.parseInt(blockNumber.substring(2),16);
        String txSum = transactionCount.getTotalTransactionCount().getTxSum();
        int tx = Integer.parseInt(txSum.substring(2),16);
        return block+","+tx;
    }
}
