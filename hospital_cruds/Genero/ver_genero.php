<?php
include('../conexion.php');
$link=conectar();
$sql="select * from genero";
$res=$link->query($sql);
 if($res->num_rows>0){
     $spec['genero']=array();
     while($row=$res->fetch_array()) {
        array_push($spec['genero'],
        array('Id_Genero'=>$row['Id_Genero'],
        'Nombre_Genero'=>$row['Nombre_Genero']));
     }
     echo json_encode($spec);
 }
 ?>