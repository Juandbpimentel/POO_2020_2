package mobs;
//import java.util.Random;

import java.util.Random;

public class Vampiro extends Mob {
    public static String corCritico = "\u001B["+ "31" + "m";

    public Vampiro(int nivel){
        this.nivel = nivel;
        this.nome = "Vampiro";
        this.vivo = true;
        this.vidaMax=0;
        this.manaMax=0;
        this.pocoes = nivel/4;
        this.dinheiro = ( 5 * (long) Math.pow(2,this.nivel));
        this.gerarItens();
        if (pocoes<=0) {
            pocoes =1;
        }
        if(nivel>1){
            int div = (nivel-1) /2;
            int div2 = (nivel-1)-div;
            this.constituicao = 1 + div;
            this.inteligencia = 1+ div2;
            this.forca = 1 ;    
        }else{
            this.constituicao = 1;
            this.inteligencia = 1;
            this.forca = 1;
        }
        for(int i = 0; i < nivel; i++){
            this.vidaMax += ( (this.constituicao + 5) * nivel);
            this.manaMax += ( (this.inteligencia + 2) * nivel);
        }

        this.vida=vidaMax;
        this.mana=manaMax;

        if(this.nivel >= 1 &&  this.nivel < 50) {
            this.nome += " Noviço";
        }else if(this.nivel>=50 && this.nivel<250){
            this.nome += " Faminto";
        }else if(this.nivel>=250 && this.nivel<500){
            this.nome = "Principe "+nome;
        }else if(this.nivel>=500 && this.nivel<1000){
            this.nome += " Primordial";
        }else if(this.nivel>=1000){
            this.nome = "O Pai do Vampiros";
        }
    }
    // x = 1/2 = 0 ; y = 1-x = 1-0 = 1;
    // x = 3/2 = 1 ; y = 2-x = 3-1 = 2;
    // x = 5/2 = 2 ; y = 5-x = 5-2 = 1;
    public Vampiro(int nivel,String nome){
        this.nivel = nivel;
        this.nome = nome;
        this.vivo = true;
        this.vidaMax=0;
        this.manaMax=0;
        this.pocoes = nivel/4;
        this.dinheiro = ( 5 * (long) Math.pow(2,this.nivel));
        this.gerarItens();
        if (pocoes<=0) {
            pocoes =1;
        }
        if(nivel>1){
            int div = (nivel-1) /2;
            int div2 = (nivel-1)-div;
            this.constituicao = 1 + div;
            this.inteligencia = 1;
            this.forca = 1 + div2;    
        }else{
            this.constituicao = 1;
            this.inteligencia = 1;
            this.forca = 1;
        }
        for(int i = 0; i < nivel; i++){
            this.vidaMax += ( (this.constituicao + 5) * nivel);
            this.manaMax += ( (this.inteligencia + 2) * nivel);
        }

        this.vida=vidaMax;
        this.mana=manaMax;

        if(this.nivel >= 1 &&  this.nivel < 50) {
            this.nome += ", o Vampiro Noviço";
        }else if(this.nivel>=50 && this.nivel<250){
            this.nome += ", o Vampiro Faminto";
        }else if(this.nivel>=250 && this.nivel<500){
            this.nome +=", o Principe Vampiro";
        }else if(this.nivel>=500 && this.nivel<1000){
            this.nome += ", o Vampiro Primordial";
        }else if(this.nivel>=1000){
            this.nome += ", O Pai do Vampiros";
        }
    }

    public long atacar(){
        if(!vivo){
            return 0;
        }

        Random random = new Random();
        long atacar = 0;
        int rand = random.nextInt(100);
        
        if((rand+1)>1+(0.1*nivel)+(0.1*forca)){
            rand = (random.nextInt(this.nivel));
            atacar = ( this.inteligencia * ((rand+1)+this.inteligencia)+ ((arma!=null)? arma.getBonusAtaque():0));
            return atacar;
        }else{
            rand = (random.nextInt(this.nivel));
            atacar = 2*( (this.inteligencia+constituicao) * ((rand+1)+this.inteligencia)+ ((arma!=null)? arma.getBonusAtaque():0));   
            System.out.print(corCritico+"!!! O seu inimigo acertou um ataque crítico !!!");
            System.out.println(limparCorTexto);
            return atacar;    
        }
    }

    public long defender(){
        if(!vivo){
            return 0;
        }
        Random random = new Random();
        long defender = 0;
        long rand = random.nextInt(100);
        if((rand+1)>1+(0.1*nivel)){
            rand = (random.nextInt(this.nivel));

            defender = (this.constituicao *(rand+1)*this.constituicao+ ((armadura!=null)? armadura.getBonusDefesa():0) + ((escudo!=null)? escudo.getBonusDefesa():0));

        }else{
            rand = (random.nextInt(this.nivel));

            defender = (long) 4*(this.constituicao*(rand+1)*this.constituicao+ ((armadura!=null)? armadura.getBonusDefesa():0) + ((escudo!=null)? escudo.getBonusDefesa():0));   
            System.out.print(corCritico+"!!! Seu inimigo executou uma desefa perfeita !!!"); 
            System.out.println(limparCorTexto);
        }
        return defender;
    }
}