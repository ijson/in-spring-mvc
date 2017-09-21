package com.ijson.platform.generator.template;


import com.ijson.platform.generator.model.ParamsVo;
import com.ijson.platform.generator.model.TableEntity;
import com.ijson.platform.generator.util.FileOperate;

import java.util.Map;

public class PomXMlBuilder implements TemplateHanlder {

    public void execute(ParamsVo<TableEntity> vo, Map<String, String> config) {
        //List<TableEntity> tables = vo.getObjs();
        //String prefix = Validator.getDefaultStr(String.valueOf(vo.getParams("prefix")), "src/main/");
        createdPomXml(config);
    }

    public void createdPomXml(Map<String, String> config) {
        String xmlPath = config.get("fs_path") + "/";
        FileOperate.getInstance().newCreateFolder(xmlPath);
        FileOperate.getInstance().newCreateFile(xmlPath + "pom.xml", getPomStr(config));

        FileOperate.getInstance().newCreateFolder(xmlPath + "src/main/java/");
        FileOperate.getInstance().newCreateFolder(xmlPath + "src/main/resources/");
        //FileOperate.getInstance().newCreateFolder(xmlPath + "src/test/java/");
        //FileOperate.getInstance().newCreateFolder(xmlPath + "src/test/resources/");

        String jarPath = config.get("package_name");
        FileOperate.getInstance().newCreateFolder(
                xmlPath + "src/main/java/" + jarPath.replace(".", "/") + "/controller");

        String jarstr = jarPath.substring(jarPath.lastIndexOf(".") + 1);
        String sitexml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<project name=\"" + jarstr + "\">\n"
                + "   <bannerLeft>\n     <name>ijson-" + jarstr + "</name>\n"
                + "     <src>https://appfuse.dev.java.net/images/appfuse-title.gif</src>\n"
                + "     <href>http://appfuse.org</href>\n"
                + "   </bannerLeft>\n    <publishDate format=\"dd MMM yyyy\"/>\n"
                + "   <skin>\n     <groupId>org.apache.tapestry</groupId>\n"
                + "     <artifactId>maven-skin</artifactId>\n     <version>1.1</version>\n"
                + "   </skin>\n    <body>\n     ${reports}\n    </body>\n" + "</project>";
        FileOperate.getInstance().newCreateFolder(xmlPath + "src/site/");
        FileOperate.getInstance().newCreateFile(xmlPath + "src/site/site.xml", sitexml);
    }


    private String getPomStr(Map<String, String> config) {
        StringBuilder result = new StringBuilder("");
        String jarPath = config.get("package_name");
        result.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" + "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" + "    <parent>\n" + "        <artifactId>in-spring-mvc</artifactId>\n" + "        <groupId>com.ijson</groupId>\n" + "        <version>1.0.0-SNAPSHOT</version>\n" + "    </parent>\n" + "    <modelVersion>4.0.0</modelVersion>\n" + "\n" + "    <artifactId>").append(jarPath.substring(jarPath.lastIndexOf(".") + 1)).append("</artifactId>\n").append("    <packaging>jar</packaging>\n").append("\n").append("    <name>").append(jarPath.substring(jarPath.lastIndexOf(".") + 1)).append("</name>\n").append("    <url>http://www.ijson.com</url>\n").append("\n").append("\n").append("    <dependencies>\n").append("        <dependency>\n").append("            <groupId>com.google.guava</groupId>\n").append("            <artifactId>guava</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.slf4j</groupId>\n").append("            <artifactId>slf4j-api</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.projectlombok</groupId>\n").append("            <artifactId>lombok</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>ch.qos.logback</groupId>\n").append("            <artifactId>logback-classic</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>aopalliance</groupId>\n").append("            <artifactId>aopalliance</artifactId>\n").append("            <version>1.0</version>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>com.ijson</groupId>\n").append("            <artifactId>in-spring-mvc-common</artifactId>\n").append("            <version>1.0.0-SNAPSHOT</version>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.springframework</groupId>\n").append("            <artifactId>spring-core</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.springframework</groupId>\n").append("            <artifactId>spring-beans</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.springframework</groupId>\n").append("            <artifactId>spring-webmvc</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.springframework</groupId>\n").append("            <artifactId>spring-web</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.springframework</groupId>\n").append("            <artifactId>spring-context</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.springframework</groupId>\n").append("            <artifactId>spring-orm</artifactId>\n").append("            <version>4.2.6.RELEASE</version>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.springframework</groupId>\n").append("            <artifactId>spring-context-support</artifactId>\n").append("            <version>4.2.6.RELEASE</version>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>javax.servlet</groupId>\n").append("            <artifactId>servlet-api</artifactId>\n").append("            <version>2.5</version>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>javax.servlet</groupId>\n").append("            <artifactId>jstl</artifactId>\n").append("            <version>1.2</version>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>commons-logging</groupId>\n").append("            <artifactId>commons-logging</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>commons-fileupload</groupId>\n").append("            <artifactId>commons-fileupload</artifactId>\n").append("            <version>1.2</version>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>commons-beanutils</groupId>\n").append("            <artifactId>commons-beanutils</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>com.fasterxml.jackson.core</groupId>\n").append("            <artifactId>jackson-core</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>com.fasterxml.jackson.core</groupId>\n").append("            <artifactId>jackson-databind</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>com.alibaba</groupId>\n").append("            <artifactId>druid</artifactId>\n").append("            <version>1.0.11</version>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>net.sf.ehcache</groupId>\n").append("            <artifactId>ehcache-core</artifactId>\n").append("            <version>2.6.8</version>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.hibernate</groupId>\n").append("            <artifactId>hibernate-core</artifactId>\n").append("            <version>3.6.10.Final</version>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.aspectj</groupId>\n").append("            <artifactId>aspectjweaver</artifactId>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>mysql</groupId>\n").append("            <artifactId>mysql-connector-java</artifactId>\n").append("            <version>5.1.43</version>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>junit</groupId>\n").append("            <artifactId>junit</artifactId>\n").append("            <scope>test</scope>\n").append("        </dependency>\n").append("        <dependency>\n").append("            <groupId>org.springframework</groupId>\n").append("            <artifactId>spring-test</artifactId>\n").append("            <scope>test</scope>\n").append("        </dependency>\n").append("\n").append("    </dependencies>\n").append("\n").append("\n").append("</project>\n");
        return result.toString();
    }


}
