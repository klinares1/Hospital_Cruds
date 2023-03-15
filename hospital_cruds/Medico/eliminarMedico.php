<?php
if($_SERVER['REQUEST_METHOD'] == 'POST'){
include('../conexion.php');
$link=conectar();
$id=$_REQUEST['id'];
$sql="DELETE FROM medico WHERE CC_Medico='$id'";
$res=mysqli_query($link,$sql);
if($res){
    echo "Datos eliminados";
}else{
    echo "No se pudo Eliminar el Medico";
}
mysqli_close($link);
}
?>