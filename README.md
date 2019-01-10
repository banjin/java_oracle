# java_oracle
学习使用java写oracle数据库

# 将ojdbc8.jar 与java源码放在同一个目录下，并将此路径放到环境变量CLASS_PATH中
vim ~/.bash_profile

    export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home

    export PATH=$JAVA_HOME/bin:$PATH

    export CLASS_PATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:/clients/java_client

source ~/.bash_profile

# 编译
javac -cp $PWD:ojdbc8.jar:$PWD OracleClient.java
# 运行
java -cp  ./ojdbc8.jar:. OracleClient
