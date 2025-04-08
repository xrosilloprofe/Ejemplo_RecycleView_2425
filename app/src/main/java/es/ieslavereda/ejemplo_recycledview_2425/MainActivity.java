package es.ieslavereda.ejemplo_recycledview_2425;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.ieslavereda.ejemplo_recycledview_2425.model.Pais;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Pais> paises;
    private RecyclerView recyclerView;
    private Switch orden;
    private FloatingActionButton anyadirButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Context context = this;

        paises = new ArrayList<>(List.of(
                new Pais("Afganistan", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_the_Taliban.svg/360px-Flag_of_the_Taliban.svg.png","Afganistán (en pastún: افغانستان\u200E, Afġānistān; en darí: افغانستان\u200E, Afġānestān; pronunciado /avɣɒnesˈtɒn/), oficialmente el Emirato Islámico de Afganistán (en pastún: د افغانستان اسلامي امارت\u200E, Da Afġānistān Islāmī Imārāt; en darí: امارت اسلامی افغانستان\u200E, Imârat-i Islâmī-yi Afġânistân), es un país montañoso sin salida al mar ubicado en Asia del Sur. Limita con Pakistán al sur y al este, con Irán al oeste, con Turkmenistán, Uzbekistán y Tayikistán al norte, y con China al noreste, a través del corredor de Waján.[4]\u200B[5]\u200B Su forma de gobierno es la de una monarquía religiosa constituida como emirato islámico. Su capital y ciudad más grande es Kabul, con una población estimada de 4,6 millones de personas, en su mayoría pastunes, tayikos, hazaras, uzbekos y turcomanos."),
                new Pais("Albania", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Flag_of_Albania.svg/252px-Flag_of_Albania.svg.png","Albania (en albanés: Shqipëri o Shqipëria), oficialmente República de Albania (en albanés: Republika e Shqipërisë), es un país situado en el sudeste de Europa. Se encuentra en el mar Adriático y Jónico dentro del mar Mediterráneo, y comparte fronteras terrestres con Montenegro al noroeste, con Kosovo[7]\u200B al noreste, Macedonia del Norte al este, Grecia al sur; y un límite marítimo con Grecia, Montenegro e Italia al oeste. Tirana es su capital y ciudad más grande, seguida de Durrës, Vlorë y Shkodër."),
                new Pais("Alemania", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Flag_of_Germany.svg/300px-Flag_of_Germany.svg.png","Alemania (en alemán: Deutschlandⓘ), oficialmente la República Federal de Alemania (Bundesrepublik Deutschlandⓘ), es un país de Europa central y el Estado de la Unión Europea con mayor población y PIB. Constituido en Estado social y democrático de derecho, su forma de gobierno es la república parlamentaria y federal. Su capital es Berlín. Está formado por dieciséis estados federados (Bundesländer) y limita al norte con el mar del Norte, Dinamarca, Suecia (frontera marítima) y el mar Báltico; al este con Polonia y la República Checa; al sur con Austria y Suiza; y al oeste con Francia, Luxemburgo, Bélgica y los Países Bajos. El municipio Büsingen am Hochrhein, enclavado en Suiza, también forma parte de Alemania. El territorio de Alemania abarca 357 376 km² de extensión[4]\u200B y posee un clima templado. Con más de 84 millones de habitantes,[6]\u200B es el país más poblado entre los Estados de la Unión Europea, y es el hogar del tercer mayor grupo de emigrantes internacionales. En 2014 Alemania fue el segundo destino de las migraciones más popular del mundo, después de Estados Unidos"),
                new Pais("Andorra", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/19/Flag_of_Andorra.svg/257px-Flag_of_Andorra.svg.png","Andorra, nombre oficial Principado de Andorra (en catalán: Principat d'Andorra), es un microestado soberano sin litoral ubicado en el suroeste de Europa, entre la comunidad autónoma de Cataluña (España) y la región de Occitania (Francia), en el límite de la península ibérica.[9]\u200B Se constituye en Estado independiente, de derecho, democrático y social,[1]\u200B cuya forma de gobierno es el coprincipado parlamentario.[1]\u200B Su territorio está organizado en siete parroquias, con una población total de 85.101 habitantes a 1 de enero de 2024.[6]\u200B Su capital es Andorra la Vieja. Se encuentra a 125 km de Toulouse y Barcelona."),
                new Pais("Angola", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9d/Flag_of_Angola.svg/270px-Flag_of_Angola.svg.png","Angola, cuyo nombre oficial es República de Angola,[13]\u200B es un país ubicado al sur de África que tiene fronteras con Namibia por el sur, con Zambia por el sureste, con la República Democrática del Congo por el norte y el noreste y con la República del Congo por el noroeste, mientras que hacia el oeste tiene costa en el océano Atlántico. Su capital es Luanda y al norte del país se encuentra el exclave de Cabinda, que tiene fronteras con la República del Congo y con la República Democrática del Congo"),
                new Pais("Argelia", "https://upload.wikimedia.org/wikipedia/commons/thumb/7/77/Flag_of_Algeria.svg/270px-Flag_of_Algeria.svg.png","Argelia[4]\u200B (en árabe: الجزائر\u200E, al-Yazā’irⓘ; en bereber: ⴷⵣⴰⵢⴻⵔ Dzayer), oficialmente República Argelina Democrática y Popular,[12]\u200B es uno de los cincuenta y cuatro países que forman el continente africano. Constitucionalmente se define como país árabe, amazig y musulmán.[13]\u200B Su capital y ciudad más poblada es Argel. "),
                new Pais("Argentina", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/Flag_of_Argentina.svg/288px-Flag_of_Argentina.svg.png","Argentina, oficialmente República Argentina,[a]\u200B es un país soberano de América del Sur, ubicado en el extremo sur y sudeste de ese subcontinente. Adopta la forma de gobierno republicana, democrática, representativa y federal."),
                new Pais("Armenia", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Flag_of_Armenia.svg/360px-Flag_of_Armenia.svg.png","Armenia (en armenio: Հայաստան Hayastanⓘ), oficialmente la República de Armenia (Հայաստանի Հանրապետություն, Hayastani Hanrapetut'yun), es un país de Transcaucasia sin salida al mar localizado en Europa Oriental y Asia Occidental.[6]\u200B[7]\u200B Comparte frontera al oeste con Turquía, al norte con Georgia, al este con Azerbaiyán y al sur con Irán y el exclave azerbaiyano de Najicheván"),
                new Pais("Bahamas", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/93/Flag_of_the_Bahamas.svg/360px-Flag_of_the_Bahamas.svg.png","Las Bahamas,[3]\u200B[4]\u200B[5]\u200B oficialmente la Mancomunidad de las Bahamas[6]\u200B[7]\u200B[8]\u200B (en inglés: Commonwealth of The Bahamas),[9]\u200B[10]\u200B es un país insular, y uno de los 13 países que forman la América Insular o Islas del Caribe, uno de los 35 del continente americano. Su capital y ciudad más poblada es Nasáu, situada en la isla de Nueva Providencia. Por su ubicación externa al Mar Caribe, las Bahamas es uno de los grupos insulares de la región que no pertenecen a una unidad geográfica antillana, denominadas Antillas Mayores y Antillas Menores. "),
                new Pais("Belice", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Flag_of_Belize.svg/300px-Flag_of_Belize.svg.png","Belice[7]\u200B (en inglés: Belize) es un país soberano de América ubicado en el extremo noreste de América Central. Limita al norte con México y al sur y oeste con Guatemala, y el golfo de Honduras lo separa del país homónimo. La capital es la ciudad de Belmopán y la ciudad más poblada es la Ciudad de Belice, que sirvió como capital hasta el año 1970, cuando la destrucción causada por el huracán Hattie obligó al gobierno a transferir la capital a la entonces comunidad planeada de Belmopán, fundada en 1960.[8]\u200B Es el único país de América Central cuyo idioma oficial es el inglés y con una forma de gobierno organizada en una monarquía constitucional parlamentaria, donde el rey del Reino Unido ejerce como jefe de Estado y es representado en el país por un gobernador general. "),
                new Pais("Bolivia", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Flag_of_Bolivia.svg/264px-Flag_of_Bolivia.svg.png","Bolivia, oficialmente el Estado Plurinacional de Bolivia[10]\u200B (en quechua: Puliwya Achka Aylluska Mamallaqta; en aimara: Wuliwya Walja Ayllunakana Marka; en guaraní: Tetã Hetate'ýigua Mborívia), es un país soberano ubicado en la región centro occidental de América del Sur, miembro de la Comunidad Andina,[11]\u200B constituido políticamente como un Estado social plurinacional, unitario, descentralizado y con autonomías.[12]\u200B El país está organizado en nueve departamentos, ciento doce provincias [11]\u200B y una región autónoma[13]\u200B La capital oficial es Sucre,[14]\u200B que alberga al órgano judicial, mientras que la sede de Gobierno es la ciudad de La Paz, que ejerce como capital de facto y que alberga a los órganos ejecutivo, legislativo y electoral. La ciudad más poblada es Santa Cruz de la Sierra. "),
                new Pais("España", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Flag_of_Spain.svg/270px-Flag_of_Spain.svg.png","España, formalmente el Reino de España,[nota 1]\u200B es un país soberano transcontinental, constituido en Estado social y democrático de derecho y cuya forma de gobierno es la monarquía parlamentaria. Es uno de los veintisiete Estados soberanos que forman la Unión Europea. Su territorio, con capital en Madrid,[30]\u200B está organizado en diecisiete comunidades autónomas, formadas a su vez por cincuenta provincias, y dos ciudades autónomas. "),
                new Pais("EEUU", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Flag_of_the_United_States.svg/342px-Flag_of_the_United_States.svg.png","Estados Unidos[nota 2]\u200B (EE. UU.; en inglés: United States, siglas: US),[nota 3]\u200B oficialmente Estados Unidos de América (EUA; en inglés: United States of America, siglas: USA),[14]\u200B es un país soberano constituido en una república constitucional compuesta por cincuenta estados y un distrito federal. Limita al norte con Canadá y al sur, con México. Su capital es Washington D. C. y su ciudad más poblada, Nueva York. La mayor parte del país se ubica en el medio de América del Norte ―donde se encuentran sus 48 estados contiguos y Washington D. C.―, entre los océanos Pacífico y Atlántico. "),
                new Pais("Finlandia", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Flag_of_Finland.svg/295px-Flag_of_Finland.svg.png","Finlandia, oficialmente la República de Finlandia (en finés: Suomiⓘ/Suomen tasavalta; en sueco: Finland/Republiken Finland), es uno de los veintisiete Estados soberanos que forman la Unión Europea. Está situado en el noreste de Europa. Tiene fronteras al oeste con Suecia, al este con Rusia y al norte con Noruega. Por el oeste y el sur está rodeada por el mar Báltico, que la separa de Suecia y Estonia, cruzando los golfos de Botnia y Finlandia, respectivamente. La capital y ciudad más importante del país es Helsinki, y la segunda ciudad más grande y área urbana más grande es Tampere, 180 kilómetros al norte de Helsinki. "),
                new Pais("Francia", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Flag_of_France.svg/270px-Flag_of_France.svg.png","Francia (en francés: Franceⓘ), oficialmente la República Francesa (République françaiseⓘ),[6]\u200B es un país soberano transcontinental que se extiende por Europa Occidental y por regiones y territorios de ultramar en América y los océanos Atlántico, Pacífico e Índico. Es uno de los veintisiete Estados soberanos que integran la Unión Europea. Se constituye en república semipresidencialista unitaria con capital en París, la mayor ciudad del país y principal centro cultural y comercial; otras zonas urbanas importantes son Marsella, Lyon, Toulouse, Lille, Burdeos, Montpellier y Niza. La Francia metropolitana se extiende desde el río Rin hasta el océano Atlántico y desde el mar Mediterráneo hasta el canal de la Mancha y el mar del Norte. Los territorios de ultramar incluyen la Guayana Francesa en América del Sur, San Pedro y Miquelón en el Atlántico Norte, las Antillas francesas y muchas islas en Oceanía y el océano Índico. Debido a sus diversos territorios costeros, Francia posee la mayor zona económica exclusiva del mundo. Sus dieciocho regiones integrales (cinco de las cuales son de ultramar) abarcan una superficie combinada de 643 801 km2 y más de 68 millones de personas.[7]\u200B Culturalmente pertenece a la Europa latina. ")
        ));

        recyclerView = findViewById(R.id.recycled);
        orden = findViewById(R.id.switchOrden);
        anyadirButton = findViewById(R.id.anyadirButton);

        AdaptadorRV adaptador = new AdaptadorRV(this,paises,this);
        recyclerView.setAdapter(adaptador);
        //GridLayoutManager
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Gestionar los movimientos de los viewholders
        ItemTouchHelper ith = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP |
                        ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        int posIni = viewHolder.getAdapterPosition();
                        int posFin = target.getAdapterPosition();
                        Pais pais = paises.remove(posIni);
                        paises.add(posFin,pais);
                        //move item fromPos to toPos
                        recyclerView.getAdapter().notifyItemMoved(posIni,posFin);
                        return true; //true if moved, false otherwise
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        //borrar el elemento de la lista
                        int posicion = viewHolder.getAdapterPosition();
                        Pais pais = paises.remove(posicion);
                        recyclerView.getAdapter().notifyItemRemoved(posicion);
                        Snackbar.make(recyclerView,"Borrado: " + pais.getNombre(),
                                Snackbar.LENGTH_LONG)
                                .setAction("Undo", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //volvais a poner el país borrado
                                        paises.add(posicion,pais);
                                        recyclerView.getAdapter().notifyItemInserted(posicion);
                                    }
                                })
                                .show();
                    }
                }
        );
        ith.attachToRecyclerView(recyclerView);

        //Gestionar switch
        orden.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    orden.setText("desorden");
                    Collections.shuffle(paises); //barajar lista

                } else {
                    orden.setText("orden");
                    Collections.sort(paises); //ordenar la lista por orden natural, nombre ascendente
                }
                adaptador.notifyDataSetChanged();
            }
        });

        ActivityResultLauncher<Intent> activityResult =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result ->{
                            if(result.getResultCode()== Activity.RESULT_OK){
                                Pais pais = (Pais) result.getData().getExtras().getSerializable("pais");
                                paises.add(pais);
                                adaptador.notifyItemInserted(paises.size()-1);
                            } else {
                                Toast.makeText(this,"Operación de alta cancelada por el usuario",Toast.LENGTH_SHORT).show();
                            }
                        });


        anyadirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnyadirActivity.class);
                activityResult.launch(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
        int posicion = recyclerView.getChildAdapterPosition(view);
        Pais pais = paises.get(posicion);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("pais",pais);
        startActivity(intent);
    }
}