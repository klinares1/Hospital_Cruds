<?php
include('../conexion.php');
$link=conectar();
$sql="select * from sangre_rh";
$res=$link->query($sql);
 if($res->num_rows>0){
     $spec['sangre_rh']=array();
     while($row=$res->fetch_array()) {
         array_push($spec['sangre_rh'],
        array('Id_RH'=>$row['Id_RH'],
        'Nombre_RH'=>$row['Nombre_RH']));
     }
     echo json_encode($spec);
 }
 ?>