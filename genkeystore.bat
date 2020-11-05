:: Schlüsselpaar erstellen
keytool -genkeypair -keyAlg RSA -dname "CN=demo" ^
-ext san=dns:localhost,ip:127.0.0.1,ip:192.168.1.143 ^
-alias demo -validity 90 -keystore keystore.jks -keypass secret -storepass secret

:: Zertifikat exportieren
keytool -exportcert -alias demo -keystore keystore.jks -file server.cer -storepass secret

:: Zertifikat in Truststore speichern
keytool -importcert -noprompt -alias demo -file server.cer -keystore certs.jks -storepass secret
