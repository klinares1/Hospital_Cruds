<?php
if($_SERVER['REQUEST_METHOD'] == 'GET'){
include('../conexion.php');
$link=conectar();
//recibiendo el parametro enviado desde el formulario de andoid
$id=$_GET['Id_Estado'];

$sql="select * from estado where Id_Estado='$id'";
$res=$link->query($sql);
//verificar si el alumno existe
 if($link->affected_rows>0){
     while($row=$res->fetch_assoc()){
         $array=$row;
     }
     echo json_encode($array);
 }else{
     echo "No existe el estado con el codigo $id";
 }
 $res->close();
 $link->close();

}
?>