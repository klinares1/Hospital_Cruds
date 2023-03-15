<?php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
include('../conexion.php');
$id=$_REQUEST['id'];
$nom=$_REQUEST['nom'];
$ape=$_REQUEST['ape'];
$gen=$_REQUEST['gen'];
$fechan=$_REQUEST['fechan'];
$rh=$_REQUEST['rh'];
$pass=$_REQUEST['pass'];

$sql="UPDATE paciente set Nombre_Paciente='$nom',Apellido_Paciente='$ape',Id_Genero='$gen',Fecha_Nac='$fechan',Id_RH='$rh',Contraseña='$pass' WHERE CC_Paciente='$id'";
$res=mysqli_query($link,$sql);
if($res){
    echo "Se Actualizo el Paciente";
}else{
    echo "No existe el Paciente";
    
}
mysqli_close($link);
}
?>