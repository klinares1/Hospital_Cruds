<?php
include('../conexion.php');
$link=conectar();
//traemos los datos capturados en android studio
$id=$_REQUEST['id'];
$nom=$_REQUEST['nom'];
$ape=$_REQUEST['ape'];
$pass=$_REQUEST['pass'];


//realizar la consulta sql de inserccion de datos
$sql="insert into administrador values ('$id','$nom','$ape','$pass')";
//ejecutar la sentencia sql
$res=mysqli_query($link,$sql) or die("Error en la consulta $sql".mysqli_error($link));

if($res) {
    echo "Administrador Registrado";
}else{
    echo "ERROR al Registrar Administrador";
}
//cierro la conexion;
mysqli_close($link);
?>