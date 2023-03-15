<?php
include('../conexion.php');
$link=conectar();
//traemos los datos capturados en android studio
$id=$_REQUEST['id'];
$nom=$_REQUEST['nom'];
$ape=$_REQUEST['ape'];
$gen=$_REQUEST['gen'];
$esp=$_REQUEST['esp'];
$pass=$_REQUEST['pass'];

//realizar la consulta sql de inserccion de datos
$sql="insert into medico values ('$id','$nom','$ape','$gen','$esp','$pass')";
//ejecutar la sentencia sql
$res=mysqli_query($link,$sql) or die("Error en la consulta $sql".mysqli_error($link));

if($res) {
    echo "Medico Registrado";
}else{
    echo "ERROR al Registrar Medico";
}
//cierro la conexion;
mysqli_close($link);
?>