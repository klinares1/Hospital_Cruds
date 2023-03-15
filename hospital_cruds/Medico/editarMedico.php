<?php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
include('../conexion.php');
$id=$_REQUEST['id'];
$nom=$_REQUEST['nom'];
$ape=$_REQUEST['ape'];
$gen=$_REQUEST['gen'];
$esp=$_REQUEST['esp'];
$pass=$_REQUEST['pass'];

$sql="UPDATE medico set Nombre_Medico='$nom',Apellido_Medico='$ape',Id_Genero='$gen',Id_Especialidad='$esp',Contraseña='$pass' WHERE CC_Medico='$id'";
$res=mysqli_query($link,$sql);
if($res){
    echo "Se Actualizo el Medico";
}else{
    echo "No existe el Medico";
}
mysqli_close($link);
}
?>