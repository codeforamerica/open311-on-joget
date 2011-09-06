package org.joget.open311.dataplugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.joget.apps.app.service.AppUtil;
import org.joget.directory.model.Group;
import org.joget.directory.model.User;
import org.joget.directory.model.service.DirectoryManager;
import org.joget.plugin.base.Plugin;
import org.joget.plugin.base.PluginProperty;
import org.joget.plugin.property.model.PropertyEditable;
import org.joget.workflow.model.DefaultParticipantPlugin;
import org.joget.workflow.model.ParticipantPlugin;
import org.joget.workflow.model.WorkflowActivity;
import org.joget.workflow.model.service.WorkflowManager;
import org.joget.workflow.util.WorkflowUtil;

/**
 * Participant plugin for Open311 service types
 * @author Michael Yap
 */
public class ServiceParticipant extends DefaultParticipantPlugin implements Plugin, ParticipantPlugin, PropertyEditable
{
    private static String[] services = new String[] {"Litter Receptacles", "Street Defects", "Sidewalk or Curb", "Tree Maintenance", "Graffiti",
        "Illegal Dumping", "Street and Sidewalk Cleaning", "Overflowing City Receptacle or Dumpster", "Sewer Issues", "Sidewalk Cleaning",
        "Sidewalk Defect", "Street Cleaning", "Street or Sidewalk"};
    
    public String getName()
    {
        return "Open311 Service Participants";
    }

    public String getVersion()
    {
        return "1.0.0";
    }

    public String getDescription()
    {
        return "Open 311 participant plugin for service types";
    }

    public PluginProperty[] getPluginProperties()
    {
        return null;
    }
    
    public String getLabel()
    {
        return "Open311 Service Participants";
    }

    public String getClassName()
    {
        return getClass().getName();
    }

    public String getPropertyOptions()
    {
        StringBuilder prop = new StringBuilder();
        String groupList = getGroupList();
        prop.append("[{ title: 'Service Participant Mapping', properties : [");
        //Listing groups
        for(String service: ServiceParticipant.services)
        {
            prop.append("{ name: '");
            prop.append(service.replaceAll(" ", ""));
            prop.append("', label: '");
            prop.append(service);
            prop.append("', type: 'selectbox', options : [");
            prop.append(groupList);
            prop.append("]},");
        }
        prop.append("{ name: 'serviceField', label: 'Workflow Variable Name', type: 'textfield' }");
        prop.append("] }]");
        return prop.toString();
    }

    /**
     * Returns available groups in json format
     * @return String containing the available groups in json format
     */
    private String getGroupList()
    {
        StringBuilder builder = new StringBuilder();
        DirectoryManager manager = (DirectoryManager) AppUtil.getApplicationContext().getBean("directoryManager");
        Collection<Group> groups = manager.getGroupList();
        boolean first = true;
        for(Group group: groups)
        {
            if(!first)
                builder.append(",");
            builder.append("{ value: '");
            builder.append(group.getId());
            builder.append("', label: '");
            builder.append(group.getName());
            builder.append("'}");
            first = false;
        }
        return builder.toString();
    }

    public String getDefaultPropertyValues()
    {
        return "";
    }

    public Collection<String> getActivityAssignments(Map props)
    {
        Collection<String> assignments = new ArrayList<String>();
        DirectoryManager manager = (DirectoryManager) AppUtil.getApplicationContext().getBean("directoryManager");
        WorkflowManager wm = (WorkflowManager) AppUtil.getApplicationContext().getBean("workflowManager");
        WorkflowActivity activity = (WorkflowActivity) props.get("workflowActivity");
        //Retrieving selected service
        String selectedService = wm.getProcessVariable(activity.getProcessId(), (String) props.get("serviceField"));
        //Retrieving mapped group
        String groupId = (String) props.get(selectedService.replaceAll(" ", ""));
        if(!(groupId == null || "".equals(groupId)))
        {
            Collection<User> users = manager.getUserByGroupId(groupId);
            for(User user: users)
                assignments.add(user.getUsername());
        }

        return assignments;
    }
}
