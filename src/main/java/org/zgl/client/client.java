package org.zgl.client;

import okhttp3.*;
import org.zgl.error.AppErrorCode;
import org.zgl.error.GenaryAppError;
import org.zgl.jetty.Msg;
import org.zgl.logic.hall.siginin.dto.FirstBuyAwardDto;
import org.zgl.player.LoginDto;
import org.zgl.utils.ProtostuffUtils;

import java.io.*;

/**
 * @作者： big
 * @创建时间： 2018/5/18
 * @文件描述：
 */
public class client {
    public static void main(String[] args) throws IOException, InterruptedException {
        client client = new client();
        LoginDto s1 = client.post((short)2,"2,2",LoginDto.class);
        Thread.sleep(3000);
        LoginDto s2 = client.post((short)2,"1,1",LoginDto.class);
        System.out.println(s1);

    }


    public <T> T post(short cmd,String args,Class<T> tClazz){
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); DataOutputStream dos = new DataOutputStream(baos)){
            OkHttpClient client = new OkHttpClient();
            dos.writeInt(-777888);
            dos.writeShort(cmd);
            Msg msm = new Msg(args);
            byte[] buf = ProtostuffUtils.serializer(msm);
            dos.writeShort(buf.length);
            dos.write(buf);

            RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"),baos.toByteArray());
            Request request = new Request.Builder()
                    .url("http://127.0.0.1:1010")
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return resultData(response.body().byteStream(),tClazz);
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }catch (Exception e){

        }
        return null;
    }
    private <T> T resultData(InputStream inputStream,Class<T> tClazz){
        try (DataInputStream dis = new DataInputStream(inputStream)){
            int head = dis.readInt();
            if(head != -777888)
                new GenaryAppError(AppErrorCode.DATA_ERR);
            short cmdId = dis.readShort();
            short length = dis.readShort();
            byte[] buf = dis.readAllBytes();
            if(length != buf.length)
                new GenaryAppError(AppErrorCode.DATA_ERR);
            T t = ProtostuffUtils.deserializer(buf,tClazz);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
