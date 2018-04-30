package com.tastycode.remy.dialogmanager.testcases;

/**
 * common object to use in the test cases
 * 
 * @author <a href="mailto:greg.milette@baesystems.com">Greg Milette</a>
 */
public class UserSaysAndExpecteResponse
{
    private String userSays;
    private String systemShouldSaySomethingContaining;

    public UserSaysAndExpecteResponse( String userSays,
        String systemShouldSaySomethingContaining )
    {
        this.userSays = userSays;
        this.systemShouldSaySomethingContaining = systemShouldSaySomethingContaining;
    }

    public String getUserSays()
    {
        return userSays;
    }

    public String getSystemShouldSaySomethingContaining()
    {
        return systemShouldSaySomethingContaining;
    }
    
    
    
    
}
