package com.cctintl.c3dfx.controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import com.cctintl.c3dfx.converters.base.NodeConverter;
import com.cctintl.c3dfx.skins.C3DComboBoxListViewSkin;

public class C3DComboBox<T> extends ComboBox<T> {

	
	public C3DComboBox() {
		super();
		this.setCellFactory(new Callback<ListView<T>, ListCell<T>>() {
			@Override
			public ListCell<T> call(ListView<T> listView) {
				return new C3DListCell<T>();
			}
		});
	}
    @Override protected Skin<?> createDefaultSkin() {
        return new C3DComboBoxListViewSkin<T>(this);
    }

    

    /***************************************************************************
	 *                                                                         *
	 * Node Converter Propertie                                                *
	 *                                                                         *
	 **************************************************************************/
    private C3DTextField textField;
    /**
     * C3D text field is set as the editor for the ComboBox.
     * The editor is null if the ComboBox is not
     * {@link #editableProperty() editable}.
     * @since JavaFX 2.2
     */
    private ReadOnlyObjectWrapper<C3DTextField> editor;
    public final TextField getC3DEditor() { 
        return c3dEditorProperty().get(); 
    }
    public final ReadOnlyObjectProperty<C3DTextField> c3dEditorProperty() { 
        if (editor == null) {
            editor = new ReadOnlyObjectWrapper<C3DTextField>(this, "editor");
            textField = new C3DComboBoxListViewSkin.FakeFocusTextField();
            editor.set(textField);
        }
        return editor.getReadOnlyProperty(); 
    }
    
    
	/***************************************************************************
	 *                                                                         *
	 * Node Converter Propertie                                                *
	 *                                                                         *
	 **************************************************************************/
    
    /**
     * Converts the user-typed input (when the ComboBox is 
     * {@link #editableProperty() editable}) to an object of type T, such that 
     * the input may be retrieved via the  {@link #valueProperty() value} property.
     */
    public ObjectProperty<NodeConverter<T>> nodeConverterProperty() { return nodeConverter; }
    private ObjectProperty<NodeConverter<T>> nodeConverter =  new SimpleObjectProperty<NodeConverter<T>>(this, "nodeConverter", C3DComboBox.<T>defaultNodeConverter());
    public final void setNodeConverter(NodeConverter<T> value) { nodeConverterProperty().set(value); }
    public final NodeConverter<T> getNodeConverter() {return nodeConverterProperty().get(); }
            
    private static <T> NodeConverter<T> defaultNodeConverter() {
        return new NodeConverter<T>() {
			@Override public Node toNode(T object) {
				if(object == null) return null;
				StackPane selectedValueContainer = new StackPane();
				selectedValueContainer.getStyleClass().add("combo-box-selected-value-holder");
				selectedValueContainer.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
				Label selectedValueLabel;
				if(object instanceof Label) selectedValueLabel = new Label(((Label)object).getText());	
				else selectedValueLabel = new Label(object.toString());
				selectedValueLabel.setTextFill(Color.BLACK);
				selectedValueContainer.getChildren().add(selectedValueLabel);
				StackPane.setAlignment(selectedValueLabel, Pos.CENTER_LEFT);
				StackPane.setMargin(selectedValueLabel, new Insets(0,0,0,5));
				return selectedValueContainer;
			}
			@Override public T fromNode(Node node) {
				return (T) node;
			}
			@Override
			public String toString(T object) {
				if(object == null) return null;
				if(object instanceof Label) return ((Label)object).getText();
				return object.toString();
			}
        };
    }
    
    
}