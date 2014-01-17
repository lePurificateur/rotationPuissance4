package puissance4;

import java.util.Random;

public class RotationPuissance4
{
	private char[][] grille;
	private static char ROND = 'O', CROIX = 'X', VIDE=' ';
	private int nbL, nbC;
	
	public RotationPuissance4(int nbL, int nbC)
	{
		this.nbL = nbL;
		this.nbC = nbC;
		grille = new char[nbL][nbC];
		vide();
	}
	
	public void vide()
	{
		for (int i = 0 ; i < nbL ; i++)
			for (int j = 0 ; j < nbC ; j++)
				grille[i][j] = VIDE;
	}
	
	public void randomGrille()
	{
		Random r = new Random();
		for (int j = 0 ; j < nbC ; j++)
		{
			int nbJetons = r.nextInt();
			nbJetons = (nbJetons >= 0) ? nbJetons : -nbJetons;
			nbJetons %= nbL;
			for (int i = nbL - 1 ; i >= nbL - nbJetons ; i--)
				grille[i][j] = (r.nextInt() % 2 == 0) ? ROND : CROIX ;
		}
	}

	public void tourneDroite()
	{
		char[][] newGrille = new char[nbC][nbL];
		for (int i = 0 ; i < nbL ; i++)
			for (int j = 0 ; j < nbC ; j++)
				newGrille[j][nbL - i - 1] = grille[i][j];
		grille = newGrille;
		int tmp = nbL;
		nbL = nbC;
		nbC = tmp;
		gravity();
	}
	
	private void gravity(int j)
	{
		int bas = nbL - 1;
		int haut = nbL - 1;
		while(haut >= 0)
		{
			if (grille[haut][j] != VIDE && grille[bas][j] == VIDE)
			{
				grille[bas][j] = grille[haut][j];
				grille[haut][j] = VIDE;
			}
			haut --;
			if (grille[bas][j] != VIDE)
				bas--;
		}
	}
	
	private void gravity()
	{
		for (int j = 0 ; j < nbC ; j++)
			gravity(j);
	}
	
	@Override
	public String toString()
	{
		String res = "";
		for (int i = 0 ; i < nbL ; i++)
		{
			for (int j = 0 ; j < nbC ; j++)
				res += grille[i][j] + "|";
			res +=" \n";
		}
		return res;
	}
	
	public static void main(String[] args)
	{
		RotationPuissance4 r = new RotationPuissance4(6, 7);
		r.randomGrille();
		System.out.println(r);
		r.tourneDroite();
		System.out.println(r);
		
	}
}
