package edu.whut.cs.oo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.whut.cs.oo.action.BaseAction;

public abstract class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7733196247428518847L;
	private long id;
	private String name;
	private String password;
	
	transient protected List<Function> functions = new ArrayList<Function>();	
	
	public void loadFunctions() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		String[] functionClassNames = getFunctionClassNames();		
		for (String functionClassName : functionClassNames) {
			BaseAction baseAction = (BaseAction) Class.forName(functionClassName).newInstance();
			Function function = new Function();
			function.setName(baseAction.getText());
			function.setAction(baseAction);
			if (functions == null) {
				functions = new ArrayList<Function>();	
			}
			functions.add(function);
		}
	}
	
	public List<Function> getFunctions() {
		return this.functions;
	}
	
	public void addFuntion(Function function) {
		functions.add(function);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return this.getClass() + " [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
	public abstract String[] getFunctionClassNames();

}
