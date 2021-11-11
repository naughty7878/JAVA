package com.test;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "hello")
public class DemoMojo extends AbstractMojo {

    @Parameter(name = "name", defaultValue = "kiwi")
    private String name;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("hello " + name);
    }

}
