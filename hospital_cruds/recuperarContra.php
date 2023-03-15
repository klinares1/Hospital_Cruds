<?php
include('./conexion.php');
$link=conectar();
//traemos los datos capturados en android studio
$user=$_REQUEST['user'];
$nom=$_REQUEST['nom'];

$sql1="select Contraseña from administrador where CC_Administrador = $user";
$res1=mysqli_query($link,$sql1) or die("Error en la consulta $sql1".mysqli_error($link));

$sql2="select Contraseña from paciente where CC_Paciente = $user";
$res2=mysqli_query($link,$sql2) or die("Error en la consulta $sql2".mysqli_error($link));

$sql3="select Contraseña from medico where CC_Medico = $user";
$res3=mysqli_query($link,$sql3) or die("Error en la consulta $sql3".mysqli_error($link));

$sql4="select Contraseña_Admin from administrador where Nombre_Admin = $nom";
$res4=mysqli_query($link,$sql4) or die("Error en la consulta $sql4".mysqli_error($link));

$sql5="select Contraseña from paciente where Nombre_Paciente = $nom";
$res5=mysqli_query($link,$sql5) or die("Error en la consulta $sql5".mysqli_error($link));

$sql6="select Contraseña from medico where Nombre_Medico = $nom";
$res6=mysqli_query($link,$sql6) or die("Error en la consulta $sql6".mysqli_error($link));

if($res1 && $res4) {
    echo "Contraseña Administrador";
    if($res1->num_rows>0){
        $spec['passwrd']=array();
        while($row=$res1->fetch_array()) {
           array_push($spec['passwrd'],
           array('Contraseña'=>$row['Contraseña'],
           'Contraseña'=>$row['Contraseña']));
        }
        echo json_encode($spec);
    }
}
else if($res2 && $res5){
    echo "Contraseña Paciente";
    if($res2->num_rows>0){
        $spec['passwrd']=array();
        while($row=$res2->fetch_array()) {
           array_push($spec['passwrd'],
           array('Contraseña'=>$row['Contraseña'],
           'Contraseña'=>$row['Contraseña']));
        }
        echo json_encode($spec);
    }
}
else if($res3 && $res6){
    echo "Contraseña Medico";
    if($res3->num_rows>0){
        $spec['passwrd']=array();
        while($row=$res3->fetch_array()) {
           array_push($spec['passwrd'],
           array('Contraseña'=>$row['Contraseña'],
           'Contraseña'=>$row['Contraseña']));
        }
        echo json_encode($spec);
    }
}
else{
    echo "ERROR: Usuario o Contraseña erroneos...";
}
//cierro la conexion;
mysqli_close($link);
?>