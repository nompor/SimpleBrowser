package application;

import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

//JavaScriptの関数を実装するクラス
public class JSFunctionManager {
	private WebEngine engine;
	private JSFunctions functions;

	//JavaScriptの関数を実装します
	public static JSFunctionManager doCreate(WebEngine engine) {
		JSFunctionManager manager = new JSFunctionManager();
		manager.functions = manager.new JSFunctions();
		manager.engine = engine;
		JSObject obj = (JSObject) engine.executeScript("window");
		obj.setMember("_java", manager.functions);

		//関数のセッティング
		engine.executeScript("console.log = function(msg){window._java.log(msg);}");

		return manager;
	}

	public void unload() {
		JSObject obj = (JSObject) engine.executeScript("window");
		obj.removeMember("_java");
	}

	//JavaScriptからconsole.logが実行された時の処理
	public class JSFunctions{
		public void log(String s) {
			System.out.println(s);
		}
	}
}
