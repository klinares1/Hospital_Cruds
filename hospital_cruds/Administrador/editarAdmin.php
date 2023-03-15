<?php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
include('../conexion.php');
$link=conectar();
$id=$_REQUEST['id'];
$nom=$_REQUEST['nom'];
$ape=$_REQUEST['ape'];
$pass=$_REQUEST['pass'];

$sql="UPDATE administrador set Nombre_Admin='$nom',Apellido_Admin='$ape',Contraseña_Admin='$pass' WHERE CC_Administrador='$id'";
$res=mysqli_query($link,$sql);
if($res){
    echo "Se Actualizo el Administrador";
}else{
    echo "No existe el Administrador";
    
}
mysqli_close($link);
}
?>