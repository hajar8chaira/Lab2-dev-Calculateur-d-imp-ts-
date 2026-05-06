# LAB 2 - Calculateur d'Impots Locaux : Saisie et Traitement
**Cours :** Programmation Mobile : Android avec Java  
**Objectif :** Developper une application permettant de calculer dynamiquement une taxe basee sur plusieurs criteres (surface, pieces, options).

---

## Demonstration Video
Visualisation du calcul en temps reel avec les differents parametres :


[<video src="https://github.com/hajar8chaira/Lab2-dev-Calculateur-d-imp-ts-/raw/main/video.mp4" controls="controls" style="max-width: 100%;">
</video>](https://github.com/user-attachments/assets/0184dc89-347d-4b2c-9978-4e98312c80a6)


---

## Etape 1 : Conception de l'interface (XML)
L'interface utilise un `LinearLayout` pour aligner les champs de saisie. Nous utilisons des `EditText` avec des types d'entrees numeriques pour restreindre la saisie.

```xml
<LinearLayout 
    android:orientation="vertical"
    android:padding="20dp">

    <!-- Saisie de la surface -->
    <EditText
        android:id="@+id/edit_surface_maison"
        android:hint="Surface (m2)"
        android:inputType="number" />

    <!-- Saisie du nombre de pieces -->
    <EditText
        android:id="@+id/edit_nb_pieces"
        android:hint="Nombre de pieces"
        android:inputType="number" />

    <!-- Option piscine -->
    <CheckBox
        android:id="@+id/check_piscine"
        android:text="Piscine" />

    <Button
        android:id="@+id/btn_calculer_impot"
        android:text="Calculer" />

    <!-- Affichage du resultat -->
    <TextView
        android:id="@+id/text_resultat_final"
        android:textSize="18sp" />
</LinearLayout>
```

---

## Etape 2 : Logique de Traitement (Java)
Le code Java gere la recuperation des donnees, la conversion des types et l'application de la formule de calcul.

```java
public class MainActivity extends AppCompatActivity {
    private EditText champSurface, champPieces;
    private CheckBox estPiscinePresente;
    private TextView labelResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        champSurface = findViewById(R.id.edit_surface_maison);
        champPieces = findViewById(R.id.edit_nb_pieces);
        estPiscinePresente = findViewById(R.id.check_piscine);
        labelResultat = findViewById(R.id.text_resultat_final);

        findViewById(R.id.btn_calculer_impot).setOnClickListener(v -> effectuerLeCalcul());
    }

    private void effectuerLeCalcul() {
        // Recuperation des chaines et conversion en nombres
        double valSurface = Double.parseDouble(champSurface.getText().toString());
        int valPieces = Integer.parseInt(champPieces.getText().toString());
        boolean aUnePiscine = estPiscinePresente.isChecked();

        // Application de la formule
        double montantGlobal = (valSurface * 2.5) + (valPieces * 45) + (aUnePiscine ? 150 : 0);

        // Affichage dynamique
        labelResultat.setText("Total a payer : " + montantGlobal + " DH");
    }
}
```

---

## Concepts Clis Appris
*   **EditText & inputType** : Filtrer la saisie utilisateur pour n'accepter que des nombres.
*   **CheckBox** : Recuperer un etat booleen (vrai/faux) pour des options facultatives.
*   **Double.parseDouble** : Convertir du texte en valeur numerique pour effectuer des operations mathematiques.
*   **String.format** (optionnel) : Permet d'arrondir le resultat pour un affichage propre des devises.

---

