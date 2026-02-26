package ma.saifdine.hd.mcpserver.tools;

import org.springaicommunity.mcp.annotation.McpArg;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class McpTools {
    @McpTool(name = "getEmployee",
            description = "Get information about a given employee ()")
    public Emplyee getEmployee(@McpArg(description = "The employee name") String name) {
        return new Emplyee(name, 12300, 4);
    }

    @McpTool(description = "Get All Employees")
    public List<Emplyee> getAllEmployees(){
        return List.of(
                new Emplyee("Hassan", 12300, 4),
                new Emplyee("Mohamed", 34000 , 1),
                new Emplyee("Imane", 23000, 10)
        );
    }
}

record Emplyee(String name, double salaire, int seniority){}

