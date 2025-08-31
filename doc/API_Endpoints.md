# MIC POWER API 端点清单

基础URL: `http://server-bwn.shuoxd.com:8080/v1/`

## 用户管理 API

### POST /user/login
**功能**: 用户登录  
**敏感级别**: 🔴 高  
**参数**: email, password (MD5加密)

### POST /user/logOut  
**功能**: 用户登出  
**敏感级别**: 🟡 中  
**参数**: email

### POST /user/sendEmailCode
**功能**: 发送邮箱验证码  
**敏感级别**: 🟡 中  
**参数**: email

### POST /user/retrievePassword
**功能**: 找回密码  
**敏感级别**: 🔴 高  
**参数**: email, verificationCode, password, confirmPassword

### POST /user/modifyPassword
**功能**: 修改密码  
**敏感级别**: 🔴 高  
**参数**: email, oldPassword, newPassword, confirmPassword

### POST /user/modifyUserInfo
**功能**: 修改用户信息  
**敏感级别**: 🟡 中  
**参数**: email, country, timeZone

### POST /user/deleteUserAccount
**功能**: 删除用户账户  
**敏感级别**: 🔴 高  
**参数**: email

### POST /user/getCountryMap
**功能**: 获取国家时区映射  
**敏感级别**: 🟢 低  
**参数**: 无

### POST /user/uploadAvatar
**功能**: 上传用户头像  
**敏感级别**: 🟢 低  
**参数**: file (multipart)

## 电站管理 API

### POST /station/stationList
**功能**: 获取用户电站列表  
**敏感级别**: 🔴 高  
**参数**: email

### POST /station/addStation
**功能**: 添加新电站  
**敏感级别**: 🔴 高  
**参数**: stationType, stationName, pvCapacity, installtionDate, country, city, address, remark, incomeUnit, energyPrice, timezone

### POST /station/updateStation
**功能**: 更新电站信息  
**敏感级别**: 🔴 高  
**参数**: stationId, stationType, stationName, pvCapacity, installtionDate, country, city, address, remark, incomeUnit, energyPrice, timezone

### POST /station/checkNetworkingDatalog
**功能**: 检查数据采集器网络状态  
**敏感级别**: 🟡 中  
**参数**: datalogSn, stationId, datalogType

## 设备数据 API

### POST /station/getBatteryData
**功能**: 获取电池设备数据  
**敏感级别**: 🔴 高  
**参数**: deviceSn, datalogSn

### POST /station/getUniversalBatteryData
**功能**: 获取通用电池数据  
**敏感级别**: 🔴 高  
**参数**: deviceSn, datalogSn

### POST /station/getInverterData
**功能**: 获取逆变器数据  
**敏感级别**: 🔴 高  
**参数**: deviceSn, datalogSn

### POST /station/getUniversalInverterData
**功能**: 获取通用逆变器数据  
**敏感级别**: 🔴 高  
**参数**: deviceSn, datalogSn

## 故障管理 API

### POST /station/faultList/{pageNum}
**功能**: 获取历史故障列表（分页）  
**敏感级别**: 🟡 中  
**参数**: stationId, pageNum (URL参数)

### POST /station/realfaultList/{pageNum}
**功能**: 获取实时故障列表（分页）  
**敏感级别**: 🟡 中  
**参数**: stationId, pageNum (URL参数)

### POST /station/getFaultById
**功能**: 根据ID获取历史故障详情  
**敏感级别**: 🟡 中  
**参数**: faultId

### POST /station/getRealFaultById
**功能**: 根据ID获取实时故障详情  
**敏感级别**: 🟡 中  
**参数**: faultId

### POST /station/updateFaultReadStatus
**功能**: 更新故障阅读状态  
**敏感级别**: 🟢 低  
**参数**: stationId

### POST /station/realFaultAllRead
**功能**: 标记所有实时故障为已读  
**敏感级别**: 🟢 低  
**参数**: stationId

## 运营管理 API

### POST /manageapp/device/getDeviceList
**功能**: 获取设备列表（运营端）  
**敏感级别**: 🔴 高  
**参数**: pageNum, pageSize, email, datalogSn, inverterSn, deviceType

### POST /manageapp/device/getDatalogList
**功能**: 获取数据采集器列表（运营端）  
**敏感级别**: 🔴 高  
**参数**: pageNum, pageSize, email, datalogSn, inverterSn, deviceType

## 请求格式

### 通用请求头
```
Content-Type: application/x-www-form-urlencoded
Accept-Language: [zh|en|fr|de]
sign: [MD5签名]
```

### 签名算法
```
MD5(请求参数字符串 + 固定密钥)
```

### 示例请求
```bash
curl -X POST \
  http://server-bwn.shuoxd.com:8080/v1/user/login \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -H 'Accept-Language: en' \
  -H 'sign: [计算得出的MD5值]' \
  -d 'email=user@example.com&password=[MD5加密的密码]'
```

## 安全注意事项

🔴 **所有API都通过HTTP明文传输**  
🔴 **包含大量敏感数据的端点**  
🔴 **签名密钥硬编码在客户端**  
🟡 **缺乏速率限制和访问控制**  

---
**文档生成时间**: 2025年8月31日
