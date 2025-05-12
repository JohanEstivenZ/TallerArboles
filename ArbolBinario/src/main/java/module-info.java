module org.example.arbolbinario {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.arbolbinario to javafx.fxml;
    exports org.example.arbolbinario;
}