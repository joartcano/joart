package tooltwist.joart.widgets;

import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.JavascriptCodeInserter;
import tooltwist.wbd.PageImportCodeInserter;
import tooltwist.wbd.SnippetParam;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdIntegerProperty;
import tooltwist.wbd.WbdNavPointProperty;
import tooltwist.wbd.WbdRenderHelper;
import tooltwist.wbd.WbdSizeInfo;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;
import tooltwist.wbd.WbdWidgetController;
import tooltwist.wbd.WbdProductionHelper;
//import tooltwist.joart.productionHelpers.GoogleMapProductionHelper;
import com.dinaa.ui.UimData;
import com.dinaa.ui.UimHelper;

/**
 * googleMap
 */
public class GoogleMapWidget extends WbdWidgetController
{
	private static final String SNIPPET_PREVIEW = "googleMap_preview.html";
	private static final String SNIPPET_DESIGN = "googleMap_design.html";
	private static final String SNIPPET_PRODUCTION = "googleMap_production.jsp";
	private static final boolean USE_PRODUCTION_HELPER = false;

	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		instance.defineProperty(new WbdStringProperty("elementId", null, "Id", ""));
	
		instance.defineProperty(new WbdStringProperty("mapWidth", null, "Map Width", "500"));
		instance.defineProperty(new WbdStringProperty("mapHeight", null, "Map Height", "380"));
		
		instance.defineProperty(new WbdStringProperty("mapLatitude", null, "Map Latitude", "500"));
		instance.defineProperty(new WbdStringProperty("mapLonghitude", null, "Map Longhitude", "380"));
//		instance.defineProperty(new WbdNavPointProperty("navpoint", null, "Navpoint", ""));
	}
	
	@Override
	public void getCodeInserters(WbdGenerator generator, WbdWidget instance, UimData ud, CodeInserterList codeInserterList) throws WbdException
	{
//TODO: Uncomment this as required
		GenerationMode mode = generator.getMode();
		if (mode == GenerationMode.DESIGN)
		{
			// Add code inserters for design mode
			CodeInserter[] arr = {

//				// Include a CSS snippet
//				new StylesheetCodeInserter(instance.miscellaneousFilePath(generator, "googleMap_cssHeader.css")),
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PREVIEW)
		{
			// Add code inserters for preview mode
			CodeInserter[] arr = {
//				// Link to an external Javascript file
//				new JavascriptLinkInserter(jsUrl),

//				// Link to an external stylesheet
//				new StylesheetLinkInserter(cssUrl),

//				// Include a javascript snippet 
//				new JavascriptCodeInserter(instance.miscellaneousFilePath(generator, "googleMap_jsHeader.js")),

//				// Include a CSS snippet
//				new StylesheetCodeInserter(instance.miscellaneousFilePath(generator, "googleMap_cssHeader.css")),
			};
			codeInserterList.add(arr);
		}
		else if (mode == GenerationMode.PRODUCTION || generator.getMode() == GenerationMode.CONTROLLER)
		{
			// Add code inserters for production mode
			CodeInserter[] arr = {
//				// Link to an external Javascript file
//				new JavascriptLinkInserter(jsUrl),
					
//				// Link to an external stylesheet
//				new StylesheetLinkInserter(cssUrl),
					
//				// Include a javascript snippet 
//				new JavascriptCodeInserter(instance.miscellaneousFilePath(generator, "googleMap_jsHeader.js")),
					
//				// Include a CSS snippet
//				new StylesheetCodeInserter(instance.miscellaneousFilePath(generator, "googleMap_cssHeader.css")),

//				// Add import statements to the JSP
//				new PageImportCodeInserter(XData.class.getName()),
			};
			codeInserterList.add(arr);

			if (USE_PRODUCTION_HELPER)
			{
				SnippetParam[] productionHelperParams = null;
//				codeInserterList.add(WbdProductionHelper.codeInserter(instance, GoogleMapProductionHelper.class.getName(), productionHelperParams));
//				codeInserterList.add(new PageImportCodeInserter(GoogleMapProductionHelper.class.getName()));
			}
		}

	}
	
	@Override
	public String getLabel(WbdWidget instance) throws WbdException
	{
		return "GoogleMap";
	}
	
	@Override
	public WbdSizeInfo getSizeInfo(WbdGenerator generator, WbdWidget instance) throws WbdException
	{
		return WbdSizeInfo.unknownSizeInfo();
	}
	
	@Override
	public void renderForPreview(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException
	{
		rh.renderSnippetForStaticPage(generator, instance, SNIPPET_PREVIEW, getSnippetParams(generator, instance, ud));
	}
	
	@Override
	public void renderForDesigner(WbdGenerator generator, WbdWidget instance, UimData ud, WbdRenderHelper rh) throws WbdException
	{
		rh.renderSnippetForStaticPage(generator, instance, SNIPPET_DESIGN, getSnippetParams(generator, instance, ud));
	}
	
	@Override
	public void renderForJSP(WbdGenerator generator, WbdWidget instance, UimHelper ud, WbdRenderHelper rh) throws Exception
	{
		rh.beforeProductionCode(generator, instance, getSnippetParams(generator, instance, ud), USE_PRODUCTION_HELPER);
		rh.renderSnippetForProduction(generator, instance, SNIPPET_PRODUCTION);
		rh.afterProductionCode(generator, instance);
	}
	
	private SnippetParam[] getSnippetParams(WbdGenerator generator, WbdWidget instance, UimData ud) throws WbdException {
		String MapWidth = instance.getProperty("mapWidth", null);
		String MapHeight = instance.getProperty("mapHeight", null);
		String MapLatitude = instance.getProperty("mapLatitude", null);
		String MapLonghitude = instance.getProperty("mapLonghitude", null);
//		String myNavpoint = instance.getProperty("myNavpoint", null);
		SnippetParam[] params = {
			new SnippetParam("MapWidth", MapWidth),
			new SnippetParam("MapHeight", MapHeight),
			new SnippetParam("MapLatitude", MapLatitude),
			new SnippetParam("MapLonghitude", MapLonghitude),
		};
		return params;
	}
}
