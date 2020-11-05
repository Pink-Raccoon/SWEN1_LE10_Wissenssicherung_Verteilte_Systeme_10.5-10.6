@echo off
java --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.rmi/sun.rmi.transport=ALL-UNNAMED --add-opens java.base/java.io=ALL-UNNAMED -cp target/swen1_le10_chat-1.0-SNAPSHOT.jar;./libs/tomcat_embed/*;./libs/gson/* ch.zhaw.soe.swen1.le10.chat.api.ChatServerMain %1 %2 %3
