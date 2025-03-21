# 使用 Debian Bullseye 作为基础镜像
FROM debian:bullseye AS build

# 安装 OpenJDK 17 和 Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven && \
    rm -rf /var/lib/apt/lists/*

# 设置工作目录
WORKDIR /app

# 将 Maven 和 Gradle 的配置缓存到本地以加快后续构建速度
RUN mkdir ~/.m2 && mkdir ~/.gradle

# 复制 pom.xml 文件
COPY pom.xml .

# 下载依赖项（这一步可以在缓存中加速后续构建）
RUN if [ -f pom.xml ]; then mvn dependency:go-offline; fi

# 复制应用源代码
COPY . .

# 构建应用（确保使用正确的打包命令）
RUN ./mvnw clean package -DskipTests

# 准备运行时环境
FROM openjdk:17

# 设置工作目录
WORKDIR /app

# 从构建阶段复制 JAR 文件到运行时镜像
COPY --from=build /app/target/*.jar /app/app.jar

# 暴露应用程序使用的端口（根据实际情况修改）
EXPOSE 8080

# 启动应用程序
ENTRYPOINT ["java","-jar","/app/app.jar"]
