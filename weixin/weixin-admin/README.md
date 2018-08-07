微信管家系统
http://kotall2018.frp2.chuantou.org/wechat/wxRequest


https://github.com/lzxz1234/WeiXin


         GET
Servlet ─────> CertifyService
   |    POST                  RAW                                 event                         CLICK
   └─────────> EncryptRouter ─────> RawMessageRouter ────────────────────────────> EventRouter ─────────> ClickEventAdaptor
                     | AES            ↑         |  text                                 |       LOCATION
                     └────> AesMessageRouter    ├───────────> TextMessageAdaptor        ├───────────────> LocationEventAdaptor
                                                |  image                                |       SCAN
                                                ├───────────> ImageMessageAdaptor       ├───────────────> ScanQrCodeEventAdaptor
                                                |  video                                |       VIEW
                                                ├───────────> VideoMessageAdaptor       ├───────────────> ViewEventAdaptor
                                                |  voice                                |       subscribe
                                                ├───────────> VoiceMessageAdaptor       ├───────────────> SubscribeEventAdaptor
                                                |  location                             |       unsubscribe
                                                ├───────────> LocationMessageAdaptor    ├───────────────> UnSubscribeEventAdaptor
                                                |  link                                 |       component_verify_ticket
                                                ├───────────> LinkMessageAdaptor        ├───────────────> ComponentVerifyTicketAdaptor
                                                |  shortvideo                           |       unauthorized
                                                └───────────> ShortVideoMessageAdaptor  └───────────────>UnAuthorizedAdaptor