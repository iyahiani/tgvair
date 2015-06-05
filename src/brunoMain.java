import java.util.ArrayList;

public class brunoMain {

   public static void main(String[] args) {

      // Création d'un train en parsant un fichier SSIM fictif

      ArrayList<String> ssimFictive = new ArrayList<>();

      // Train1 à prendre
      ssimFictive.add("1TRAIN COMPANY SCHEDULES TAPE SNCF                                                                                                                                                                             00000001");
      ssimFictive.add("2LSN  0008E00 01JAN1512DEC1515APR15HORAIRES  ETE 2008  DE :   BR01JAN15C0004              FIC PRODUIT PAR MOTRICE POUR KHT/SSIM                                                                                00000002");
      ssimFictive.add("3 SN00000101W26APR1510MAY15      7 CHZWE07130713+0100  CHWIL07180718+0100  TICB                                                           70  FSN000001                                                      00000003");
      ssimFictive.add("4 PWAPWAPWAPWAPWAPWAPWPAWPA      7 CHZWE07130713+0100  CHWIL07180718+0100  TICB                                                           70  FSN000001                                                      00000003");

      // ITrain train=ITrainFactory.createTrain

   }
}
