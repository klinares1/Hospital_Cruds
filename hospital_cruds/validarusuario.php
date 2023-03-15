<?php
include('./conexion.php');
$link=conectar();
//traemos los datos capturados en android studio
$CC_administrador=$_POST['CC_Administrador'];
$contraseña_Admin=$_POST['Contraseña_Admin'];

$sentencia=$conexion->prepare("SELECT * FROM administrador WHERE CC_administrador=".$CC_administrador." AND contraseña_Admin=".$contraseña_Admin);
//$sentencia->bind_param('ss',$CC_administrador,$contraseña_Admin);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()){
    echo json_encode($fila,JSON_UNESCAPED_UNICODE);
}
$sentencia->close();
$conexion->close();
mysqli_close($link);
?>