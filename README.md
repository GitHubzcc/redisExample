# redisExample

#### 介绍
redis场景化使用

#### 系统架构
src

    main
    
        pos -----定位代码
        rank ----等级排行代码
    test
        pos -----定位测试
        rank ----等级排行测试
#### 软件架构
springboot、redisGEO


#### 安装教程

确保redis客户端版本>3.0

#### 使用说明

在test目录下执行即可

#### 命令
更新某个成员的值，使用geoadd添加即可覆盖原member的值，方法返回0
