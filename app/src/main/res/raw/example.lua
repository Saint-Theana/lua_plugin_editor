local robot = {};
--主要api
robot.api = {};
----------API注释
--SendGroupMessage(MessageFactory factory)                  --发送群消息
--SendGroupImage(MessageFactory factory)                     --发送群图片
--SendGroupPtt(MessageFactory factory)                        --发送群语音
--SendGroupLongMessage(MessageFactory factory)              --发送群长文本消息
--SendFriendMessage(MessageFactory factory)                  --发送好友消息
--SendFriendImage(MessageFactory factory)                     --发送好友图片
--SendFriendPtt(MessageFactory factory)                        --发送好友语音
--SendFriendLongMessage(MessageFactory factory)              --发送好友长文本消息
--SendTempolaryMessage(MessageFactory factory)              --发送临时会话消息
--SendTempolaryImage(MessageFactory factory)                 --发送临时会话图片
--SendTempolaryPtt(MessageFactory factory)                    --发送临时会话语音
--SendTempolaryLongMessage(MessageFactory factory)          --发送临时会话长文本消息
--DealGroupRequest(MessageFactory factory)                    --处理加群请求
--GroupMemberShutUp(MessageFactory factory)                 --群成员禁言
--GroupMemberDelete(MessageFactory factory)                  --群成员踢出
--GroupShutUp(MessageFactory factory)                         --群全体禁言
--UpdateGroupList(MessageFactory factory)                      --更新群列表
--GetGroupList(MessageFactory factory)                         --获取群列表


--次要api
robot.extraapi = {};
----reload(,robot.name())                         --重新载入本插件
----log("测试")                                   --打印日志
----getmessagefactory()                         --返回MessageFactory对象
----getatstore()                                  --返回AtStore对象
----getfileentry()                                 --获取文件对象
----gethttpentry("https://m.baidu.com")           --获取http对象


----------MessageFactory注释
----message_type      消息类型 0:文本/1:xml/2:json/3:图片/4:语音
----group_uin          群号，发送群消息、群全体禁言时必须标明
----message           消息内容(文本/xml/json/语音图片文件路径)
----friend_uin          好友号，发送好友消息必须标明
----group_code         群代码，非群号，发送临时会话必须标明
----target_uin          目标号，禁言、处理加群请求、发送临时会话、踢人时必须标明
----isshutup           是否禁言，开启全体禁言时必须标明，true为开启禁言，false为关闭禁言
----shuttime           禁言时间，群成员禁言时必须标明，单位分钟
----ispass             是否同意，处理加群请求时必须标明，true为同意，false为拒绝
----requestid           请求id，处理加群请求时必须标明
----at_list              需要at的人员名单，仅在文本消息中生效

----------AtStore注释
----target_uin          语言艾特的目标qq
----at_name           显示艾特的名字，可以随便写

----java接口
----------httpentry类注释
----set_timeout(5000)                                       --设置超时
----set_method("GET")                                      --默认为GET可设置为POST
----set_data("name=Tick_Tock&outlook=super_handsome")     --设置post上传的信息，GET模式下不起作用
----set_header("User-Agent","Mozila......")                      --设置请求头
----request()                                               --打开链接获取返回内容
----以上set_xxx方法全部返回本体对象，可连串写 例如.setxxx().set_xxx().set_xxx()


----------fileenty类注释
----getcurrentpath()                                         --获取当前路径
----getfilelist("/sdcard/ZZPTT")                               --获取路径的文件列表
----isfileexist("/sdcard/ZZPTT/1.silk")                         --文件是否存在
----readfromfile("/sdcard/ZZPTT/1.txt")                        --读取文件内容
----writetofile("文本","/sdcard/1.txt")                           --将文本写入文件




----接收好友消息事件，必须实现该入口
---------FriendMessage注释
----message_type            消息类型 0 文本|1 图片|2 语音|3 xml|4 json
----font_name               字体名称
----sender_uin               发送者qq号
----message                 消息内容
----self_uin                  机器人自身qq
function robot.onfriendmessage(msg)
    local factory = robot.extraapi:getmessagefactory()
    factory.friend_uin=msg.sender_uin
    factory.message_type=0;
    if(msg.message == "测试")
        then
            factory.message="测试成功"
            robot.api:SendFriendMessage(factory)
    end
end



----接收群临时消息事件，必须实现该入口
---------TempolaryMessage注释
----message_type            消息类型 0 文本|1 图片|2 语音|3 xml|4 json
----font_name               字体名称
----sender_uin               发送者qq号
----group_uin                发送者所在群
----message                 消息内容
----self_uin                  机器人自身qq
----group_code              群代码
function robot.ontempolarymessage(msg)
    local factory = robot.extraapi:getmessagefactory()
    factory.target_uin=msg.sender_uin
    factory.message_type=0;
    factory.group_code=msg.group_code;
    if(msg.message == "测试")
        then
            factory.message="测试成功"
            robot.api:SendTempolaryMessage(factory)
    end
end


----处理入群请求事件，必须实现该入口
---------TempolaryMessage注释
----group_uin                群号
----request_id                请求id
----message                 请求内容
----requestor                请求者qq
----request_time             请求时间
----ispassed                 是否已通过
function robot.onrequestmessage(msg)
    local factory = robot.extraapi:getmessagefactory()
	factory.requestid=msg.request_id;
	factory.ispass=true;
	factory.group_uin=msg.group_uin;
	factory.target_uin=msg.requestor;
	robot.api:DealGroupRequest(factory);
end



----接收群消息事件，必须实现该入口
---------GroupMessage注释
----message_type            消息类型 0 文本|1 图片|2 语音|3 xml|4 json|5 文件
----font_name               字体名称
----sender_uin               发送者qq号
----sender_name             发送者昵称
----self_uin                  机器人自身qq
----group_uin                消息来自群号
----group_name              群名称
----message                 消息内容
----message_time            消息发送时间
----at_list                    被艾特的成员列表
function robot.ongroupmessage(msg)
    local factory = robot.extraapi:getmessagefactory()
    factory.group_uin=msg.group_uin
    factory.message_type=0;
    if(msg.message == "测试")
        then
            factory.message="测试成功"
            robot.api:SendGroupMessage(factory)
    end
end


--处理好友消息

----返回插件名，必须实现该入口
function robot.name()
    return 'lua测试'
end



----插件加载入口 传入api 已线程化，必须实现该入口
function robot.load(api,extraapi)
    robot.api=api;
    robot.extraapi=extraapi;
end

----返回robot对象，不可删除
return robot