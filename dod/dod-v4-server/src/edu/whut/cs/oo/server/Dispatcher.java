package edu.whut.cs.oo.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import edu.whut.cs.oo.common.Constants;
import edu.whut.cs.oo.common.Message;
import edu.whut.cs.oo.controller.BaseController;
import edu.whut.cs.oo.exception.BaseException;

public class Dispatcher {
	
	private static HashMap<String, BaseController> controllers = new HashMap<String, BaseController>();
	
	static {
		for (String controllerClassname : Constants.CONTROLLER_CLASSNAMES) {
			BaseController baseController;
			try {
				baseController = (BaseController) Class.forName(controllerClassname).newInstance();
				controllers.put(baseController.getClass().getName(), baseController);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void forward(ObjectInputStream in, ObjectOutputStream out) throws IOException, ClassNotFoundException, BaseException {
		Message message = (Message)in.readObject(); // read new message
		BaseController controller = controllers.get(message.getController());
		controller.setOut(out);
		controller.setIn(in);
		controller.setMessage(message);
		System.out.println(controller.getClass().getSimpleName() + " is serving ......");
		controller.service();
	}

}
