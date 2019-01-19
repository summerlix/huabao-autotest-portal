package com.king.service.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StrUtils
{
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	/**
	字符转json对象,不能含有某些非法字符，否则会出现"\\u201",到c++端就会乱码
    */
	public static String  removeStrIllegalCharacterForToJson(String str)                                         //获取本机当前的时间
	{
		String regEx="[……——‘”“’]"; 
		Pattern p = Pattern.compile(regEx); 
		Matcher m = p.matcher(str);
		str=m.replaceAll("").trim();
		
		return str;
	}
	
	/**两个字符串的比较,包括字符串为null的情况
	 * @param  
	 * @return
	 */
	public static Boolean  compareStringIncludeNull(String str1,String str2)
	{
		boolean bool;
	    if(str1==null)
	    {
	    	bool=(str1==str2);
	    }else
	    {
	    	bool=str1.equals(str2);
	    }
	    
		return bool;
	}
	 
	/**判断是否是整数
	 * @param  
	 * @return
	 */
	public static Boolean  StringIsInteger(String str)
	{ 
		return  str.matches("[0-9]+");   
	}
	
	public static double  convertRateToValue(String rateStr)
	{
		NumberFormat nf=NumberFormat.getPercentInstance();//NumberFormat是一个工厂，可以直接getXXX创建，而getPercentInstance()

		Number m=null;
		try 
		{
			m = nf.parse(rateStr);
		} catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return m.doubleValue();
	}
	
	/**
	 * 今日流程自动化任务解密
	 * @param aa
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	 
	public static Map<String, String> messageDecode(byte[] aa) throws UnsupportedEncodingException
    {
        byte[] compress = EncryptUtils.decompress(Arrays.copyOfRange(aa, 20, aa.length));
        int index = 0;
        boolean isName = true;
        boolean isString = false;
        String key=null;
        String value=null;
        Map<String, String> result = new HashMap<>();
        for (int i=0;i<compress.length;i++)
        {
            if (compress[i]==0 && isName)
            {
                byte[] copyOfRange = Arrays.copyOfRange(compress, index, i);
                key = new String(copyOfRange,"GBK");
                i++;
                index=i;
                isName=false;
                byte[] copyOfRange1 = Arrays.copyOfRange(compress, index, i+1);
                i++;
                index=i;
                if (copyOfRange1[0]==4) isString=true;
            }
            if (compress[i]==0 && isString)
            {
                byte[] copyOfRange3 = Arrays.copyOfRange(compress, index, i);
                i++;
                index=i;
                byte[] bytes = Arrays.copyOfRange(compress, index, i + Integer.parseInt(new String(copyOfRange3)));
                value = new String(bytes,"GBK");
                isName = true;
                i=i+Integer.parseInt(new String(copyOfRange3));
                index=i;
                isString=false;
            }
            if  (compress[i]==0 && !isString)
            {
                byte[] copyOfRange3 = Arrays.copyOfRange(compress, index, i);
                value = new String(copyOfRange3,"GBK");
                i++;
                index=i;
                isName = true;
            }
            result.put(key, value);
        }

        return result;
    }
        
	/**
	 * 自动化运维http推事件解密
	 * @param encodeString
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, String> messageDecode(String encodeString) throws UnsupportedEncodingException
    {
        byte[] decodeString = java.util.Base64.getDecoder().decode(encodeString.replace("\r\n", ""));
        byte[] compress = EncryptUtils.decompress(Arrays.copyOfRange(decodeString, 20, decodeString.length));
        int index = 0;
        boolean isName = true;
        boolean isString = false;
        String key=null;
        String value=null;
        Map<String, String> result = new HashMap<>();
        for (int i=0;i<compress.length;i++)
        {
            if (compress[i]==0 && isName)
            {
                byte[] copyOfRange = Arrays.copyOfRange(compress, index, i);
                key = new String(copyOfRange,"GBK");
                i++;
                index=i;
                isName=false;
                byte[] copyOfRange1 = Arrays.copyOfRange(compress, index, i+1);
                i++;
                index=i;
                if (copyOfRange1[0]==4) isString=true;
            }
            if (compress[i]==0 && isString)
            {
                byte[] copyOfRange3 = Arrays.copyOfRange(compress, index, i);
                i++;
                index=i;
                byte[] bytes = Arrays.copyOfRange(compress, index, i + Integer.parseInt(new String(copyOfRange3)));
                value = new String(bytes,"GBK");
                isName = true;
                i=i+Integer.parseInt(new String(copyOfRange3));
                index=i;
                isString=false;
            }
            if  (compress[i]==0 && !isString)
            {
                byte[] copyOfRange3 = Arrays.copyOfRange(compress, index, i);
                value = new String(copyOfRange3,"GBK");
                i++;
                index=i;
                isName = true;
            }
            result.put(key, value);
        }

        return result;
    }
	
	 public static String txtToString(File  file)
	 {
       StringBuilder result = new StringBuilder();
       
       if(!file.exists())                                             //如果文件不存在，返回""
       {
       	return "";
       }
       
       try
       {
           BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
           String s = null;
           int i=0;
           while((s = br.readLine())!=null)
           {//使用readLine方法，一次读一行
           	if (i==0) 
           	{
           		result.append(s);
				}else
				{
					result.append(System.lineSeparator()+s);
				}
            i++;   
           }
           br.close();    
       }catch(Exception e){
           e.printStackTrace();
       }
       
       return result.toString();
	 }

}
