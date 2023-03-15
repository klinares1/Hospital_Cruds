<?php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
include('../conexion.php');
$link=conectar();
$id=$_REQUEST['id'];
$sql="DELETE FROM administrador WHERE CC_Administrador='$id'";
$res=mysqli_query($link,$sql);
if($res){
    echo "Datos eliminados";
}else{
    echo "No se pudo Eliminar el Administrador";
}
mysqli_close($link);
}
?>