package org.joget.open311.dataplugin;

import java.util.Map;

import org.joget.apps.app.service.AppUtil;
import org.joget.apps.form.model.Element;
import org.joget.apps.form.model.FormBuilderPaletteElement;
import org.joget.apps.form.model.FormData;
import org.joget.apps.form.service.FormUtil;


public class CustomFormField extends Element implements FormBuilderPaletteElement {

	@Override
	public String renderTemplate(FormData formData, Map dataModel) {
		String template = "map.ftl";
        String value = FormUtil.getElementPropertyValue(this, formData);
        dataModel.put("value", value);
        String html = FormUtil.generateElementHtml(this, formData, template, dataModel);
        return html;
	}

	public String getFormBuilderCategory() {
		return "Basic";
	}

	public String getFormBuilderIcon() {
		return null;
	}

	public int getFormBuilderPosition() {

		return 5000;
	}

	public String getFormBuilderTemplate() {
		return "<label class='label'>Map</label><input type='text' />";
	}

	public String getDefaultPropertyValues() {
		return "{label:'Map'}";
	}

	public String getLabel() {
		return "Map";
	}

	public String getPropertyOptions() {

		return AppUtil.readPluginResource(getClass().getName(), "/properties/form/map.json", null, true, "message/form/Map");
	}

	public String getDescription() {

		return "Google Map";
	}

	public String getName() {
		return "Map";
	}

	public String getVersion() {
		return "1.0.6";
	}
	

}
