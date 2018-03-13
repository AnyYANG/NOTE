package cn.liuyang;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("myresource")
public class PathTest
{
    @GET
    @Produces()
    public String getIt(){

        return "Got it";
    }
}
