package Core.Components;

import java.util.List;

public interface BaseComponent extends Updatable {
	public List<BaseComponent> getChildren();
	void addChild(BaseComponent child);
	void removeChild(BaseComponent child);
	public BaseComponent getParent();
	void setParent(BaseComponent parent);
	BaseComponent createCopy();
}
