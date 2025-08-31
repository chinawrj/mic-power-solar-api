# MIC POWER 光伏监控APP API分析报告

## 概述
本报告基于对逆向工程的MIC POWER光伏监控应用程序的代码分析，详细记录了该应用的网络通信协议、API端点和安全特性。

**分析日期**: 2025年8月31日  
**应用名称**: MIC POWER  
**应用版本**: 20250822.1  
**框架**: uni-app 4.75  

## 关键发现

### 🔴 核心安全发现
**该应用使用HTTP协议进行所有主要业务API通信，而非HTTPS**

## 网络配置详情

### 主要API服务器
```javascript
baseUrl: "http://server-bwn.shuoxd.com:8080/v1/"
```

- **协议**: HTTP
- **域名**: server-bwn.shuoxd.com
- **端口**: 8080
- **API版本**: v1

### 请求签名机制
- **算法**: MD5
- **签名密钥**: <固定密钥，已在客户端常量中实现；公开仓库不直接披露>
- **签名格式**: hex_md5(请求参数 + 固定密钥)

### 请求头配置
```javascript
header: {
    "Content-Type": "application/x-www-form-urlencoded",
    "Accept-Language": [动态语言代码],
    "sign": [MD5签名值]
}
```

## API端点清单

### 用户认证模块
| 端点 | 方法 | 功能 | 敏感数据 |
|------|------|------|----------|
| `user/login` | POST | 用户登录 | ✅ 邮箱/密码 |
| `user/logOut` | POST | 用户登出 | - |
| `user/sendEmailCode` | POST | 发送邮箱验证码 | ✅ 邮箱地址 |
| `user/retrievePassword` | POST | 找回密码 | ✅ 密码重置 |
| `user/modifyPassword` | POST | 修改密码 | ✅ 旧密码/新密码 |
| `user/modifyUserInfo` | POST | 修改用户信息 | ✅ 个人信息 |
| `user/deleteUserAccount` | POST | 删除用户账户 | ✅ 账户信息 |
| `user/getCountryMap` | POST | 获取国家映射 | - |
| `user/uploadAvatar` | POST | 上传头像 | - |

### 电站管理模块
| 端点 | 方法 | 功能 | 敏感数据 |
|------|------|------|----------|
| `station/stationList` | POST | 获取电站列表 | ✅ 电站信息 |
| `station/addStation` | POST | 添加电站 | ✅ 电站配置 |
| `station/updateStation` | POST | 更新电站信息 | ✅ 电站配置 |
| `station/checkNetworkingDatalog` | POST | 检查数据采集器状态 | ✅ 设备状态 |

### 设备数据模块
| 端点 | 方法 | 功能 | 敏感数据 |
|------|------|------|----------|
| `station/getBatteryData` | POST | 获取电池数据 | ✅ 设备数据 |
| `station/getUniversalBatteryData` | POST | 获取通用电池数据 | ✅ 设备数据 |
| `station/getInverterData` | POST | 获取逆变器数据 | ✅ 设备数据 |
| `station/getUniversalInverterData` | POST | 获取通用逆变器数据 | ✅ 设备数据 |

### 故障管理模块
| 端点 | 方法 | 功能 | 敏感数据 |
|------|------|------|----------|
| `station/faultList/{pageNum}` | POST | 获取故障列表 | ✅ 故障信息 |
| `station/realfaultList/{pageNum}` | POST | 获取实时故障列表 | ✅ 实时故障 |
| `station/getFaultById` | POST | 获取故障详情 | ✅ 故障详情 |
| `station/getRealFaultById` | POST | 获取实时故障详情 | ✅ 实时故障详情 |
| `station/updateFaultReadStatus` | POST | 更新故障阅读状态 | - |
| `station/realFaultAllRead` | POST | 标记所有实时故障为已读 | - |

### 运营管理模块  
| 端点 | 方法 | 功能 | 敏感数据 |
|------|------|------|----------|
| `manageapp/device/getDeviceList` | POST | 获取设备列表 | ✅ 设备信息 |
| `manageapp/device/getDatalogList` | POST | 获取数据采集器列表 | ✅ 采集器信息 |

## 蓝牙连接功能

### BLE设备发现
- **库**: FastBLE
- **功能**: 发现和连接光伏数据采集器
- **设备类型**: 支持CT和4G采集器

## 技术架构

### 前端框架
- **主框架**: uni-app 4.75
- **UI库**: uView UI
- **网络库**: luch-request (基于uni.request)

### 后端集成
- **网络工具**: NetTool.java (支持HTTP/HTTPS)
- **SSL支持**: 已配置但未用于主业务API
- **请求处理**: OkHttp3集成

## 安全风险评估

### 🔴 高风险项目
1. **明文传输**: 所有敏感数据通过HTTP明文传输
2. **凭据暴露**: 用户登录凭据可被中间人攻击截获
3. **设备数据泄露**: 光伏设备运行数据缺乏加密保护
4. **签名密钥硬编码**: MD5签名密钥在代码中明文存储

### ⚠️ 中风险项目
1. **API端点暴露**: 所有API端点可被枚举和分析
2. **请求重放**: 缺乏时间戳等防重放机制
3. **会话管理**: 会话令牌通过HTTP传输

### ✅ 安全措施
1. **请求签名**: 使用MD5签名验证请求完整性
2. **语言本地化**: 支持多语言请求头
3. **SSL基础设施**: 代码层面支持HTTPS（但未启用）

## 建议

### 立即建议
1. **启用HTTPS**: 将baseUrl更改为HTTPS协议
2. **证书固定**: 实施SSL证书固定以防止中间人攻击
3. **签名升级**: 将MD5签名升级为更安全的HMAC-SHA256

### 长期建议
1. **API版本控制**: 实施更严格的API版本管理
2. **访问控制**: 添加基于角色的访问控制
3. **审计日志**: 实施详细的API访问日志记录

## 验证方法

### 网络抓包验证
```bash
# 使用Charles Proxy或Wireshark监控HTTP流量
# 目标: http://server-bwn.shuoxd.com:8080
```

### API测试验证
```bash
# 测试API端点可达性
curl -I http://server-bwn.shuoxd.com:8080/v1/
```

### 签名算法验证
```javascript
// MD5签名生成示例
const crypto = require('crypto');
const key = '<env:MIC_SIGNATURE_KEY>';
const params = 'email=test@example.com&password=123456';
const signature = crypto.createHash('md5').update(params + key).digest('hex');
```

---

**免责声明**: 本分析仅用于安全研究和教育目的。请在合法授权范围内使用相关信息。
