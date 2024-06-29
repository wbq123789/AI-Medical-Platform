package org.platform_fisco;

import jakarta.annotation.Resource;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.junit.jupiter.api.Test;
import org.platform_fisco.entity.transaction;
import org.platform_fisco.service.FileService;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class PlatformFiscoApplicationTests {

    private final String[] address= {
            "0x99adfece32cab5beddfce607b4fe9f0efb68c36a",
            "0xae850a94d6386c7ba01c25a71569b6f08dcf062b",
            "0x1ecf754033848e87fb14656c37fc05794def836b",
            "0x6d56a0009585d00b9afa5963431208f880f46ddd",
            "0xa73bb13bd2f201c773e979262e25ea245bbae52f"
    };
    public final String configFile ="src/main/resources/config.toml";
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
                transactionProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(clients.get(i-1), keyPair, "src/main/resources/abi/", "src/main/resources/bin/");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processors.add(transactionProcessor);
        }
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
        for (int i = 0; i <object.size() ; i++) {
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

    @Test
    void contextLoads() throws Exception {
        List<transaction> transactions = this.selectById(1, "001");
        for (transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

}
