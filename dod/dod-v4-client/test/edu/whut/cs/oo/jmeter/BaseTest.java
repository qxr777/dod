package edu.whut.cs.oo.jmeter;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import edu.whut.cs.oo.common.Constants;
import edu.whut.cs.oo.exception.BaseException;
  
public abstract class BaseTest extends AbstractJavaSamplerClient {
   
 // 测试结果
 protected SampleResult sr;
   
 /**
  * 初始化
  */
 public void setupTest(JavaSamplerContext arg0) {  
        System.out.println("setupTest");  
    }
   
 /**
  * 设置请求的参数
  */
 public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("ip", Constants.SERVER_ADDRESS);
        params.addArgument("port", Constants.SERVER_LISTEN_PORT + "");
        return params;
    }
   
 /**
  * 运行过程
  */
 public SampleResult runTest(JavaSamplerContext arg0) {
        sr = new SampleResult();  
        try{  
            sr.sampleStart(); //记录程序执行时间，以及执行结果  
            //发送数据  
            System.out.println("begin");  
            service();
    		System.out.println("end");
            
            
        }catch(Throwable e){  
            sr.setSuccessful(false);  
        }finally{  
            sr.sampleEnd();  
        }  
        return sr; 
 }
 
 public abstract void service() throws BaseException;
   
 /**
  * 结束
  */
 public void teardownTest(JavaSamplerContext arg0) {
    }
   
}