package org.example.testInfinispanCoreDependencyWithJgroup;

import org.infinispan.Cache;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.example.testInfinispanCoreDependencyWithJgroupJar.MyClass.execute;
import static org.example.testInfinispanCoreDependencyWithJgroupJar.MyClass.extractVersion;

@WebServlet("/HelloWorld")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final PrintWriter writer = resp.getWriter();
        writer.println("Cache package: " + extractVersion(Cache.class));
        execute();
        writer.close();
    }
}
