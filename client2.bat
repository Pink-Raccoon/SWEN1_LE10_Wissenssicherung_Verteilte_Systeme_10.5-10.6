@echo off
REM set PATH_TO_FX="path\to\javafx-sdk\lib"
java -cp target/swen1_le10_chat-1.0-SNAPSHOT.jar --module-path "%PATH_TO_FX%" --add-modules javafx.controls ch.zhaw.soe.swen1.le10.chat.presentation.gui.ChatClientMain %1 %2 %3 