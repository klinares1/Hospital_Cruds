<?php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
include('../conexion.php');
$link=conectar();
$id=$_REQUEST['id'];
$sql="DELETE FROM paciente WHERE CC_Paciente='$id'";
$res=mysqli_query($link,$sql);
if($res){
    echo "Datos eliminados";
}else{
    echo "No se pudo Eliminar el Paciente";
}
mysqli_close($link);
}
?>