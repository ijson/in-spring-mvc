package com.ijson.platform.generator.template;


import com.ijson.platform.common.util.SystemUtil;
import com.ijson.platform.generator.model.ParamsVo;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.util.FileOperate;

public class PomXMlBuilder implements TemplateHanlder {

    public void execute(ParamsVo<TableEntity> vo) {
        //List<TableEntity> tables = vo.getObjs();
        //String prefix = Validator.getDefaultStr(String.valueOf(vo.getParams("prefix")), "src/main/");
        createdPomXml();
    }

    public void createdPomXml() {
        String xmlPath = SystemUtil.getInstance().getConstant("fs_path") + "/";
        FileOperate.getInstance().newCreateFolder(xmlPath);
        FileOperate.getInstance().newCreateFile(xmlPath + "pom.xml", getPomStr());

        FileOperate.getInstance().newCreateFolder(xmlPath + "src/main/java/");
        FileOperate.getInstance().newCreateFolder(xmlPath + "src/main/resources/");
        //FileOperate.getInstance().newCreateFolder(xmlPath + "src/test/java/");
        //FileOperate.getInstance().newCreateFolder(xmlPath + "src/test/resources/");

        String jarPath = SystemUtil.getInstance().getConstant("package_name");
        FileOperate.getInstance().newCreateFolder(
                xmlPath + "src/main/java/" + jarPath.replace(".", "/") + "/controller");

        String jarstr = jarPath.substring(jarPath.lastIndexOf(".") + 1);
        String sitexml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<project name=\"" + jarstr + "\">\n"
                + "   <bannerLeft>\n     <name>datamining-" + jarstr + "</name>\n"
                + "     <src>https://appfuse.dev.java.net/images/appfuse-title.gif</src>\n"
                + "     <href>http://appfuse.org</href>\n"
                + "   </bannerLeft>\n    <publishDate format=\"dd MMM yyyy\"/>\n"
                + "   <skin>\n     <groupId>org.apache.tapestry</groupId>\n"
                + "     <artifactId>maven-skin</artifactId>\n     <version>1.1</version>\n"
                + "   </skin>\n    <body>\n     ${reports}\n    </body>\n" + "</project>";
        FileOperate.getInstance().newCreateFolder(xmlPath + "src/site/");
        FileOperate.getInstance().newCreateFile(xmlPath + "src/site/site.xml", sitexml);
    }

    private String getPomStr() {
        StringBuffer result = new StringBuffer("");
        String jarPath = SystemUtil.getInstance().getConstant("package_name");
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \r\n");
        result.append("<project \r\n");
        result
                .append("	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\"\r\n");
        result
                .append("	xmlns=\"http://maven.apache.org/POM/4.0.0\"                                                         \r\n");
        result
                .append("	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">                                            \r\n");
        result
                .append("	<modelVersion>4.0.0</modelVersion>                                                                \r\n");
        result
                .append("	<groupId>datamining</groupId>                                                                     \r\n");
        result.append("	<artifactId>" + jarPath.substring(jarPath.lastIndexOf(".") + 1)
                + "</artifactId>                                                                     \r\n");
        result
                .append("	<version>1.0</version>                                                                            \r\n");
        result
                .append("	<packaging>jar</packaging>                                                                        \r\n");
        result.append("	<name>" + jarPath.substring(jarPath.lastIndexOf(".") + 1)
                + "</name>                                                                                 \r\n");
        result
                .append("	<url>http://www.datamining.cn</url>                                                               \r\n");
        result
                .append("	<properties>                                                                                      \r\n");
        result
                .append("		<project.build.sourceEncoding>                                                                  \r\n");
        result
                .append("			UTF-8                                                                                         \r\n");
        result
                .append("		</project.build.sourceEncoding>                                                                 \r\n");
        result
                .append("		<poi.version>3.9</poi.version>                                                                  \r\n");
        result
                .append("		<mail.version>1.4.4</mail.version>                                                              \r\n");
        result
                .append("		<spring.framework.version>                                                                      \r\n");
        result
                .append("			3.1.0.RELEASE                                                                                 \r\n");
        result
                .append("		</spring.framework.version>                                                                     \r\n");
        result
                .append("		<hibernate.version>3.6.8.Final</hibernate.version>                                              \r\n");
        result
                .append("		<cxf.version>2.7.3</cxf.version>                                                                \r\n");
        result
                .append("		<java.version>1.7</java.version>                                                                \r\n");
        result
                .append("		<druid.version>1.0.11</druid.version>                                                           \r\n");
        result
                .append("	</properties>                                                                                     \r\n");
        result
                .append("	<dependencies>                                                                                    \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>datamining</groupId>                                                                 \r\n");
        result
                .append("			<artifactId>springmvc-db</artifactId>                                                         \r\n");
        result
                .append("			<version>1.0.20151123</version>                                                                        \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");

        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>datamining</groupId>                                                                 \r\n");
        result
                .append("			<artifactId>springmvc-framework</artifactId>                                                         \r\n");
        result
                .append("			<version>1.0</version>                                                                        \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");

        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>datamining</groupId>                                                                 \r\n");
        result
                .append("			<artifactId>springmvc-tools</artifactId>                                                      \r\n");
        result
                .append("			<version>1.0</version>                                                                        \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>datamining</groupId>                                                                 \r\n");
        result
                .append("			<artifactId>springmvc-api</artifactId>                                                        \r\n");
        result
                .append("			<version>1.0.20151123</version>                                                                        \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>datamining</groupId>                                                                 \r\n");
        result
                .append("			<artifactId>springmvc-cache</artifactId>                                                      \r\n");
        result
                .append("			<version>1.0.20151123</version>                                                                        \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("	<!-- web项目引入jar begin-->                                                                      \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>javax.servlet</groupId>                                                              \r\n");
        result
                .append("			<artifactId>servlet-api</artifactId>                                                          \r\n");
        result
                .append("			<version>2.5</version>                                                                        \r\n");
        result
                .append("			<scope>provided</scope>                                                                       \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>javax.servlet.jsp</groupId>                                                          \r\n");
        result
                .append("			<artifactId>jsp-api</artifactId>                                                              \r\n");
        result
                .append("			<version>2.1</version>                                                                        \r\n");
        result
                .append("			<scope>provided</scope>                                                                       \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>javax.servlet</groupId>                                                              \r\n");
        result
                .append("			<artifactId>jstl</artifactId>                                                                 \r\n");
        result
                .append("			<version>1.2</version>                                                                        \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>taglibs</groupId>                                                                    \r\n");
        result
                .append("			<artifactId>standard</artifactId>                                                             \r\n");
        result
                .append("			<version>1.1.2</version>                                                                      \r\n");
        result
                .append("		</dependency><!-- web项目引入jar end --><!-- tools begin -->                                    \r\n");
        result
                .append("		<!-- 伪链接辅助 -->                                                                             \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.tuckey</groupId>                                                                 \r\n");
        result
                .append("			<artifactId>urlrewritefilter</artifactId>                                                     \r\n");
        result
                .append("			<version>4.0.4</version>                                                                      \r\n");
        result
                .append("		</dependency><!-- jdom-xml操作工具包 -->                                                        \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>jdom</groupId>                                                                       \r\n");
        result
                .append("			<artifactId>jdom</artifactId>                                                                 \r\n");
        result
                .append("			<version>1.1</version>                                                                        \r\n");
        result
                .append("		</dependency><!-- dom4j-xml操作工具包 -->                                                       \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>dom4j</groupId>                                                                      \r\n");
        result
                .append("			<artifactId>dom4j</artifactId>                                                                \r\n");
        result
                .append("			<version>1.6.1</version>                                                                      \r\n");
        result
                .append("		</dependency><!-- junit测试工具jar -->                                                          \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>junit</groupId>                                                                      \r\n");
        result
                .append("			<artifactId>junit</artifactId>                                                                \r\n");
        result
                .append("			<version>4.11</version>                                                                       \r\n");
        result
                .append("		</dependency><!-- 缓存工具jar -->                                                               \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>net.sf.ehcache</groupId>                                                             \r\n");
        result
                .append("			<artifactId>ehcache-core</artifactId>                                                         \r\n");
        result
                .append("			<version>2.6.8</version>                                                                      \r\n");
        result
                .append("		</dependency><!-- log4j日志jar -->                                                              \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>log4j</groupId>                                                                      \r\n");
        result
                .append("			<artifactId>log4j</artifactId>                                                                \r\n");
        result
                .append("			<version>1.2.14</version>                                                                     \r\n");
        result
                .append("		</dependency><!-- net.sf.json工具jar-->                                                         \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>net.sf.json-lib</groupId>                                                            \r\n");
        result
                .append("			<artifactId>json-lib</artifactId>                                                             \r\n");
        result
                .append("			<version>2.4</version>                                                                        \r\n");
        result
                .append("			<classifier>jdk15</classifier>                                                                \r\n");
        result
                .append("		</dependency><!-- log4j 辅助工具jar begin -->                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.slf4j</groupId>                                                                  \r\n");
        result
                .append("			<artifactId>slf4j-api</artifactId>                                                            \r\n");
        result
                .append("			<version>1.7.1</version>                                                                      \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.slf4j</groupId>                                                                  \r\n");
        result
                .append("			<artifactId>slf4j-log4j12</artifactId>                                                        \r\n");
        result
                .append("			<version>1.7.1</version>                                                                      \r\n");
        result.append("		</dependency><!-- log4j 辅助工具jar end --><!-- 邮件工具 -->                                    \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>javax.mail</groupId>                                                                 \r\n");
        result
                .append("			<artifactId>mail</artifactId>                                                                 \r\n");
        result
                .append("			<version>${mail.version}</version>                                                            \r\n");
        result
                .append("		</dependency><!-- 导入导出工具 -->                                                              \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.apache.poi</groupId>                                                             \r\n");
        result
                .append("			<artifactId>poi</artifactId>                                                                  \r\n");
        result
                .append("			<version>${poi.version}</version>                                                             \r\n");
        result
                .append("		</dependency><!-- 定时器工具 -->                                                                \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.quartz-scheduler</groupId>                                                       \r\n");
        result
                .append("			<artifactId>quartz</artifactId>                                                               \r\n");
        result
                .append("			<version>1.8.5</version>                                                                      \r\n");
        result
                .append("		</dependency><!-- 缓存使用jar -->                                                               \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>com.google.guava</groupId>                                                           \r\n");
        result
                .append("			<artifactId>guava</artifactId>                                                                \r\n");
        result
                .append("			<version>r09</version>                                                                        \r\n");
        result
                .append("		</dependency><!-- 对象转换jar -->                                                               \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>net.sf.ezmorph</groupId>                                                             \r\n");
        result
                .append("			<artifactId>ezmorph</artifactId>                                                              \r\n");
        result
                .append("			<version>1.0.6</version>                                                                      \r\n");
        result
                .append("		</dependency><!-- 映射工具jar -->                                                               \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>net.sf.dozer</groupId>                                                               \r\n");
        result
                .append("			<artifactId>dozer</artifactId>                                                                \r\n");
        result
                .append("			<version>5.3.2</version>                                                                      \r\n");
        result
                .append("		</dependency><!-- oracle数据驱动 -->                                                            \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>com.oracle</groupId>                                                                 \r\n");
        result
                .append("			<artifactId>ojdbc14</artifactId>                                                              \r\n");
        result
                .append("			<version>10.2.0.3.0</version>                                                                 \r\n");
        result
                .append("		</dependency><!-- mysql数据驱动 -->                                                             \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>mysql</groupId>                                                                      \r\n");
        result
                .append("			<artifactId>mysql-connector-java</artifactId>                                                 \r\n");
        result
                .append("			<version>5.1.26</version>                                                                     \r\n");
        result
                .append("		</dependency><!-- db end --><!-- tools end --><!-- commons begin -->                            \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>commons-dbcp</groupId>                                                               \r\n");
        result
                .append("			<artifactId>commons-dbcp</artifactId>                                                         \r\n");
        result
                .append("			<version>1.4</version>                                                                        \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>commons-lang</groupId>                                                               \r\n");
        result
                .append("			<artifactId>commons-lang</artifactId>                                                         \r\n");
        result
                .append("			<version>2.6</version>                                                                        \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>commons-collections</groupId>                                                        \r\n");
        result
                .append("			<artifactId>commons-collections</artifactId>                                                  \r\n");
        result
                .append("			<version>3.2.1</version>                                                                      \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>commons-beanutils</groupId>                                                          \r\n");
        result
                .append("			<artifactId>commons-beanutils</artifactId>                                                    \r\n");
        result
                .append("			<version>1.8.3</version>                                                                      \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>commons-fileupload</groupId>                                                         \r\n");
        result
                .append("			<artifactId>commons-fileupload</artifactId>                                                   \r\n");
        result
                .append("			<version>1.2.2</version>                                                                      \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>commons-httpclient</groupId>                                                         \r\n");
        result
                .append("			<artifactId>commons-httpclient</artifactId>                                                   \r\n");
        result
                .append("			<version>3.1</version>                                                                        \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>commons-io</groupId>                                                                 \r\n");
        result
                .append("			<artifactId>commons-io</artifactId>                                                           \r\n");
        result
                .append("			<version>2.4</version>                                                                        \r\n");
        result
                .append("		</dependency><!-- commons end --><!-- spring begin -->                                          \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.springframework</groupId>                                                        \r\n");
        result
                .append("			<artifactId>spring-aop</artifactId>                                                           \r\n");
        result
                .append("			<version>${spring.framework.version}</version>                                                \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.springframework</groupId>                                                        \r\n");
        result
                .append("			<artifactId>spring-jdbc</artifactId>                                                          \r\n");
        result
                .append("			<version>${spring.framework.version}</version>                                                \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.springframework</groupId>                                                        \r\n");
        result
                .append("			<artifactId>spring-orm</artifactId>                                                           \r\n");
        result
                .append("			<version>${spring.framework.version}</version>                                                \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.springframework</groupId>                                                        \r\n");
        result
                .append("			<artifactId>spring-beans</artifactId>                                                         \r\n");
        result
                .append("			<version>${spring.framework.version}</version>                                                \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.springframework</groupId>                                                        \r\n");
        result
                .append("			<artifactId>spring-context</artifactId>                                                       \r\n");
        result
                .append("			<version>${spring.framework.version}</version>                                                \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.springframework</groupId>                                                        \r\n");
        result
                .append("			<artifactId>spring-context-support</artifactId>                                               \r\n");
        result
                .append("			<version>${spring.framework.version}</version>                                                \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.springframework</groupId>                                                        \r\n");
        result
                .append("			<artifactId>spring-core</artifactId>                                                          \r\n");
        result
                .append("			<version>${spring.framework.version}</version>                                                \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.springframework</groupId>                                                        \r\n");
        result
                .append("			<artifactId>spring-tx</artifactId>                                                            \r\n");
        result
                .append("			<version>${spring.framework.version}</version>                                                \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.springframework</groupId>                                                        \r\n");
        result
                .append("			<artifactId>spring-webmvc</artifactId>                                                        \r\n");
        result
                .append("			<version>${spring.framework.version}</version>                                                \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.springframework</groupId>                                                        \r\n");
        result
                .append("			<artifactId>spring-web</artifactId>                                                           \r\n");
        result
                .append("			<version>${spring.framework.version}</version>                                                \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.springframework</groupId>                                                        \r\n");
        result
                .append("			<artifactId>spring-test</artifactId>                                                          \r\n");
        result
                .append("			<version>${spring.framework.version}</version>                                                \r\n");
        result
                .append("		</dependency><!-- spring辅助工具jar begin-->                                                    \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>javassist</groupId>                                                                  \r\n");
        result
                .append("			<artifactId>javassist</artifactId>                                                            \r\n");
        result
                .append("			<version>3.12.1.GA</version>                                                                  \r\n");
        result
                .append("		</dependency><!-- spring辅助工具jar end--><!-- spring end -->                                   \r\n");
        result
                .append("		<!-- hibernate begin -->                                                                        \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.hibernate</groupId>                                                              \r\n");
        result
                .append("			<artifactId>hibernate-core</artifactId>                                                       \r\n");
        result
                .append("			<version>${hibernate.version}</version>                                                       \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.hibernate</groupId>                                                              \r\n");
        result
                .append("			<artifactId>hibernate-ehcache</artifactId>                                                    \r\n");
        result
                .append("			<version>${hibernate.version}</version>                                                       \r\n");
        result
                .append("		</dependency><!-- hibernate 集成辅助jar begin-->                                                \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.codehaus.jackson</groupId>                                                       \r\n");
        result
                .append("			<artifactId>jackson-core-asl</artifactId>                                                     \r\n");
        result
                .append("			<version>1.9.2</version>                                                                      \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.codehaus.jackson</groupId>                                                       \r\n");
        result
                .append("			<artifactId>jackson-mapper-asl</artifactId>                                                   \r\n");
        result
                .append("			<version>1.9.2</version>                                                                      \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.aspectj</groupId>                                                                \r\n");
        result
                .append("			<artifactId>aspectjweaver</artifactId>                                                        \r\n");
        result
                .append("			<version>1.6.12</version>                                                                     \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>cglib</groupId>                                                                      \r\n");
        result
                .append("			<artifactId>cglib-nodep</artifactId>                                                          \r\n");
        result
                .append("			<version>2.2.2</version>                                                                      \r\n");
        result
                .append("		</dependency><!-- hibernate 集成辅助jar end--><!-- hibernate end -->                            \r\n");
        result
                .append("		<!-- mybatis begin-->                                                                           \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.mybatis</groupId>                                                                \r\n");
        result
                .append("			<artifactId>mybatis</artifactId>                                                              \r\n");
        result
                .append("			<version>3.2.5</version>                                                                      \r\n");
        result
                .append("		</dependency>                                                                                   \r\n");
        result
                .append("		<dependency>                                                                                    \r\n");
        result
                .append("			<groupId>org.mybatis</groupId>                                                                \r\n");
        result
                .append("			<artifactId>mybatis-spring</artifactId>                                                       \r\n");
        result
                .append("			<version>1.2.1</version>                                                                      \r\n");
        result
                .append("		</dependency><!-- mybatis end --><!-- cxf begin -->                                             \r\n");
        result
                .append("		<!--dependency>                                                                                 \r\n");
        result.append("	<groupId>org.apache.cxf</groupId>               \r\n");
        result.append("	<artifactId>cxf-rt-frontend-jaxws</artifactId>     \r\n");
        result.append("	<version>${cxf.version}</version>               \r\n");
        result.append("	</dependency>                                          \r\n");
        result.append("	<dependency>                                                  \r\n");
        result.append("	<groupId>org.apache.cxf</groupId>                                    \r\n");
        result.append("	<artifactId>cxf-rt-transports-common</artifactId>                           \r\n");
        result.append("	<version>2.5.4</version>                                                           \r\n");
        result
                .append("	</dependency>                                                                             \r\n");
        result.append("	<dependency>                       \r\n");
        result.append("	<groupId>org.apache.cxf</groupId>         \r\n");
        result.append("	<artifactId>cxf-rt-core</artifactId>             \r\n");
        result.append("	<version>${cxf.version}</version>                       \r\n");
        result.append("	</dependency>                                                  \r\n");
        result.append("	<dependency>             \r\n");
        result.append("	<groupId>org.apache.cxf</groupId>\r\n");
        result.append("	<artifactId>cxf-rt-transports-http-jetty</artifactId>       \r\n");
        result.append("	<version>${cxf.version}</version>                        \r\n");
        result.append("	</dependency!-->\r\n");
        result
                .append("		<!-- cxf end -->                                                                                \r\n");
        result
                .append("	</dependencies>                                                                                   \r\n");
        result
                .append("	<!-- 资源数据下载 begin -->                                                                       \r\n");
        result
                .append("	<repositories>                                                                                    \r\n");
        result
                .append("		<repository>                                                                                    \r\n");
        result
                .append("			<id>public</id>                                                                               \r\n");
        result
                .append("			<name>public repositories</name>                                                              \r\n");
        result
                .append("			<url>                                                                                         \r\n");
        result
                .append("				http://115.29.148.30:8081/nexus/content/groups/public                                       \r\n");
        result
                .append("			</url>                                                                                        \r\n");
        result
                .append("		</repository>                                                                                   \r\n");
        result
                .append("		<repository>                                                                                    \r\n");
        result
                .append("			<id>releases</id>                                                                             \r\n");
        result
                .append("			<name>releases</name>                                                                         \r\n");
        result
                .append("			<url>                                                                                         \r\n");
        result
                .append("				http://115.29.148.30:8081/nexus/content/repositories/releases                               \r\n");
        result
                .append("			</url>                                                                                        \r\n");
        result
                .append("		</repository>                                                                                   \r\n");
        result
                .append("	</repositories>                                                                                   \r\n");
        result
                .append("	<!-- 资源数据下载 end -->                                                                         \r\n");
        result
                .append("	<build>                                                                                           \r\n");
        result
                .append("		<finalName>${project.artifactId}</finalName>                                                    \r\n");
        result.append("		<plugins><!-- test插件, 仅测试名称为*Test的类,使用支持分组测试的surefire-junit47 driver -->     \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.apache.maven.plugins</groupId>                                                 \r\n");
        result
                .append("				<artifactId>maven-surefire-plugin</artifactId>                                              \r\n");
        result
                .append("				<version>2.12.4</version>                                                                   \r\n");
        result
                .append("				<configuration>                                                                             \r\n");
        result
                .append("					<includes>                                                                                \r\n");
        result
                .append("						<include>**/*Test.java</include>                                                        \r\n");
        result
                .append("					</includes>                                                                               \r\n");
        result
                .append("				</configuration>                                                                            \r\n");
        result
                .append("				<dependencies>                                                                              \r\n");
        result
                .append("					<dependency>                                                                              \r\n");
        result
                .append("						<groupId>org.apache.maven.surefire</groupId>                                            \r\n");
        result
                .append("						<artifactId>surefire-junit47</artifactId>                                               \r\n");
        result
                .append("						<version>2.12.4</version>                                                               \r\n");
        result
                .append("					</dependency>                                                                             \r\n");
        result
                .append("				</dependencies>                                                                             \r\n");
        result.append("			</plugin><!-- 增加更多的Source和Test Source目录插件 -->                                       \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.codehaus.mojo</groupId>                                                        \r\n");
        result
                .append("				<artifactId>build-helper-maven-plugin</artifactId>                                          \r\n");
        result
                .append("				<version>1.7</version>                                                                      \r\n");
        result.append("			</plugin><!-- cobertura 测试覆盖率统计插件 -->                                                \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.codehaus.mojo</groupId>                                                        \r\n");
        result
                .append("				<artifactId>cobertura-maven-plugin</artifactId>                                             \r\n");
        result
                .append("				<version>2.5.1</version>                                                                    \r\n");
        result.append("			</plugin><!-- war打包插件, 设定war包名称不带版本号 -->                                        \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.apache.maven.plugins</groupId>                                                 \r\n");
        result
                .append("				<artifactId>maven-war-plugin</artifactId>                                                   \r\n");
        result
                .append("				<version>2.3</version>                                                                      \r\n");
        result
                .append("				<configuration>                                                                             \r\n");
        result
                .append("					<warName>${project.artifactId}</warName>                                                  \r\n");
        result
                .append("				</configuration>                                                                            \r\n");
        result
                .append("			</plugin><!-- jar打包相关插件 -->                                                             \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.apache.maven.plugins</groupId>                                                 \r\n");
        result
                .append("				<artifactId>maven-jar-plugin</artifactId>                                                   \r\n");
        result
                .append("				<version>2.4</version>                                                                      \r\n");
        result
                .append("				<configuration>                                                                             \r\n");
        result
                .append("					<archive>                                                                                 \r\n");
        result
                .append("						<manifest>                                                                              \r\n");
        result
                .append("							<addDefaultImplementationEntries>                                                     \r\n");
        result
                .append("								true                                                                                \r\n");
        result
                .append("							</addDefaultImplementationEntries>                                                    \r\n");
        result
                .append("						</manifest>                                                                             \r\n");
        result
                .append("					</archive>                                                                                \r\n");
        result
                .append("				</configuration>                                                                            \r\n");
        result
                .append("			</plugin><!-- clean插件 -->                                                                   \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.apache.maven.plugins</groupId>                                                 \r\n");
        result
                .append("				<artifactId>maven-clean-plugin</artifactId>                                                 \r\n");
        result
                .append("				<version>2.5</version>                                                                      \r\n");
        result
                .append("			</plugin><!-- junit插件設置內存 -->                                                           \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.apache.maven.plugins</groupId>                                                 \r\n");
        result
                .append("				<artifactId>maven-surefire-plugin</artifactId>                                              \r\n");
        result
                .append("				<configuration>                                                                             \r\n");
        result
                .append("					<argLine>-Xmx512M</argLine>                                                               \r\n");
        result
                .append("				</configuration>                                                                            \r\n");
        result
                .append("			</plugin><!-- install插件 -->                                                                 \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.apache.maven.plugins</groupId>                                                 \r\n");
        result
                .append("				<artifactId>maven-install-plugin</artifactId>                                               \r\n");
        result
                .append("				<version>2.4</version>                                                                      \r\n");
        result
                .append("			</plugin><!-- eclipse插件,设定下载Source -->                                                  \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.apache.maven.plugins</groupId>                                                 \r\n");
        result
                .append("				<artifactId>maven-eclipse-plugin</artifactId>                                               \r\n");
        result
                .append("				<version>2.9</version>                                                                      \r\n");
        result
                .append("				<configuration>                                                                             \r\n");
        result
                .append("					<downloadSources>true</downloadSources>                                                   \r\n");
        result
                .append("					<downloadJavadocs>false</downloadJavadocs>                                                \r\n");
        result
                .append("				</configuration>                                                                            \r\n");
        result
                .append("			</plugin><!-- dependency相关插件 -->                                                          \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.apache.maven.plugins</groupId>                                                 \r\n");
        result
                .append("				<artifactId>maven-dependency-plugin</artifactId>                                            \r\n");
        result
                .append("				<version>2.5.1</version>                                                                    \r\n");
        result
                .append("			</plugin>                                                                                     \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.codehaus.mojo</groupId>                                                        \r\n");
        result
                .append("				<artifactId>versions-maven-plugin</artifactId>                                              \r\n");
        result
                .append("				<version>1.3.1</version>                                                                    \r\n");
        result.append("			</plugin><!-- jetty插件, 设定端口与context path -->                                           \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.mortbay.jetty</groupId>                                                        \r\n");
        result
                .append("				<artifactId>jetty-maven-plugin</artifactId>                                                 \r\n");
        result
                .append("				<configuration>                                                                             \r\n");
        result
                .append("					<webAppConfig>                                                                            \r\n");
        result
                .append("						<contextPath>                                                                           \r\n");
        result
                .append("							/${project.artifactId}                                                                \r\n");
        result
                .append("						</contextPath>                                                                          \r\n");
        result
                .append("					</webAppConfig>                                                                           \r\n");
        result
                .append("				</configuration>                                                                            \r\n");
        result.append("			</plugin><!-- 配置文件属性设置 -->                                                            \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.apache.maven.plugins</groupId>                                                 \r\n");
        result
                .append("				<artifactId>maven-resources-plugin</artifactId>                                             \r\n");
        result
                .append("				<configuration>                                                                             \r\n");
        result
                .append("					<encoding>UTF-8</encoding>                                                                \r\n");
        result
                .append("				</configuration>                                                                            \r\n");
        result.append("			</plugin>                                                                \r\n");
        result.append("			<!-- eclipse插件,是否下载源码或者doc文档 -->                                         \r\n");
        result
                .append("			<plugin>                                                                                      \r\n");
        result
                .append("				<groupId>org.apache.maven.plugins</groupId>                                                 \r\n");
        result
                .append("				<artifactId>maven-eclipse-plugin</artifactId>                                               \r\n");
        result
                .append("				<configuration>                                                                             \r\n");
        result
                .append("					<additionalProjectnatures>                                                                \r\n");
        result
                .append("						<projectnature>                                                                         \r\n");
        result
                .append("							org.springframework.ide.eclipse.core.springnature                                     \r\n");
        result
                .append("						</projectnature>                                                                        \r\n");
        result
                .append("					</additionalProjectnatures>                                                               \r\n");
        result
                .append("					<additionalBuildcommands>                                                                 \r\n");
        result
                .append("						<buildcommand>                                                                          \r\n");
        result
                .append("							org.springframework.ide.eclipse.core.springbuilder                                    \r\n");
        result
                .append("						</buildcommand>                                                                         \r\n");
        result
                .append("					</additionalBuildcommands>                                                                \r\n");
        result
                .append("					<downloadSources>true</downloadSources>                                                   \r\n");
        result
                .append("					<downloadJavadocs>false</downloadJavadocs>                                                \r\n");
        result
                .append("					<wtpversion>2.0</wtpversion>                                                              \r\n");
        result
                .append("				</configuration>                                                                            \r\n");
        result
                .append("			</plugin>                                                                                     \r\n");
        result
                .append("		</plugins>                                                                                      \r\n");
        result
                .append("	</build>                                                                                          \r\n");
        result
                .append("</project>                                                                                         \r\n");

        return result.toString();
    }
}
