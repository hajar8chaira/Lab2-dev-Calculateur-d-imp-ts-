package com.example.calculimpotlocaux;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Variables avec noms uniques
    private EditText champSurface, champPieces;
    private CheckBox estPiscinePresente;
    private TextView labelResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Liaison des composants
        champSurface = findViewById(R.id.edit_surface_maison);
        champPieces = findViewById(R.id.edit_nb_pieces);
        estPiscinePresente = findViewById(R.id.check_piscine);
        labelResultat = findViewById(R.id.text_resultat_final);
        Button boutonAction = findViewById(R.id.btn_calculer_impot);

        // Déclenchement du calcul
        boutonAction.setOnClickListener(v -> effectuerLeCalcul());
    }

    private void effectuerLeCalcul() {
        try {
            // Récupération et conversion des saisies
            String strSurface = champSurface.getText().toString();
            String strPieces = champPieces.getText().toString();

            if (strSurface.isEmpty() || strPieces.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            double valSurface = Double.parseDouble(strSurface);
            int valPieces = Integer.parseInt(strPieces);
            boolean aUnePiscine = estPiscinePresente.isChecked();

            // Formule de calcul personnalisée
            double baseImposable = valSurface * 2.5; // 2.5 DH par m²
            double fraisParPiece = valPieces * 45;   // 45 DH par pièce
            double taxePiscine = aUnePiscine ? 150 : 0; // 150 DH si piscine

            double montantGlobal = baseImposable + fraisParPiece + taxePiscine;

            // Affichage du résultat final
            labelResultat.setText("Total à payer : " + String.format("%.2f", montantGlobal) + " DH");

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Erreur de saisie numérique", Toast.LENGTH_SHORT).show();
        }
    }
}
