<?php
include('../conexion.php');
$link=conectar();
$sql="select * from especialidad";
$res=$link->query($sql);
 if($res->num_rows>0){
     $spec['especialidad']=array();
     while($row=$res->fetch_array()) {
        array_push($spec['especialidad'],
        array('Id_Especialidad'=>$row['Id_Especialidad'],
        'Nombre_Especialidad'=>$row['Nombre_Especialidad']));
     }
     echo json_encode($spec);
 }