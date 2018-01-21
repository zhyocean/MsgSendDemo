import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @author: zhangocean
 * @Date: Created in 19:25 2018/1/14
 * Describe:
 */
public class SendCode {
    public static SendSmsResponse sendSms(String phoneNumber, String code) throws ClientException {
        //设置超时时间(不必修改)
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        //(不必修改)
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient，("***"分别填写自己的AccessKey ID和Secret)
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", "***", "***");
        //(不必修改)
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        //(不必修改)
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象(不必修改)
        SendSmsRequest request = new SendSmsRequest();
        //****处填写接收方的手机号码
        request.setPhoneNumbers(phoneNumber);
        //****填写已申请的短信签名
        request.setSignName("****");
        //****填写获得的短信模版CODE
        request.setTemplateCode("****");
        //笔者的短信模版中有${code}, 因此此处对应填写验证码
        request.setTemplateParam("{\"code\":\""+code+"\"}");
        //不必修改
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
    public static void main(String[] args) throws ClientException, InterruptedException {
        SendSmsResponse response = sendSms("18380307082","55555");
    }
}
