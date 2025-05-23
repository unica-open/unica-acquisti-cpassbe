/*-
 * ========================LICENSE_START=================================
 * CPASS BackEnd - EJB submodule
 * %%
 * Copyright (C) 2019 - 2025 CSI Piemonte
 * %%
 * SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 * =========================LICENSE_END==================================
 */
package it.csi.cpass.cpassbe.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.DirectoryStream;
import java.nio.charset.StandardCharsets;

public class TaroccaLicenza {

 public static void main(String[] args) {
	 	
	 	List<String> listaDir = new ArrayList<String>();
	 	listaDir.add("C:\\myworkspace\\cpass\\cpassbe\\ejb\\src\\main\\java\\it\\csi\\");
	 	listaDir.add("C:\\myworkspace\\cpass\\cpassbe\\integ-acta\\src\\main\\java\\it\\csi\\");
	 	listaDir.add("C:\\myworkspace\\cpass\\cpassbe\\integ-hr\\src\\main\\java\\it\\csi\\");
	 	listaDir.add("C:\\myworkspace\\cpass\\cpassbe\\integ-notier\\src\\main\\java\\it\\csi\\");
	 	listaDir.add("C:\\myworkspace\\cpass\\cpassbe\\integ-siac\\src\\main\\java\\it\\csi\\");
	 	listaDir.add("C:\\myworkspace\\cpass\\cpassbe\\integ-sicraweb\\src\\main\\java\\it\\csi\\");
	 	listaDir.add("C:\\myworkspace\\cpass\\cpassbe\\integ-stilo\\src\\main\\java\\it\\csi\\");
	 	listaDir.add("C:\\myworkspace\\cpass\\cpassbe\\lib\\src\\main\\java\\it\\csi\\");
	 	listaDir.add("C:\\myworkspace\\cpass\\cpassbe\\profiles\\");
	 	listaDir.add("C:\\myworkspace\\cpass\\cpassbe\\exposed\\");
	 	
	 	
	 	
        //String directory = "C:\\myworkspace\\cpass\\cpassbe\\ejb\\src\\main\\java\\";
        //String directory = "C:\\myworkspace\\cpass\\cpassbe\\ejb\\src\\test\\java\\it\\csi\\cpass\\cpassbe\\utility\\";
        //String searchString  = "Copyright (C) 2019 - 2020 CSI Piemonte";
        //String replaceString = "Copyright (C) 2019 - 2025 CSI Piemonte";
        String searchString  = "SPDX-FileCopyrightText: Copyright 2019 - 2020 | CSI Piemonte";
        String replaceString = "SPDX-FileCopyrightText: Copyright 2019 - 2025 | CSI Piemonte";
        for(String directory :listaDir) {
	        try {
	        	
	            replaceInDirectory(Paths.get(directory), searchString, replaceString);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        }

 	}
 
	 /**
	  * 
	  * @param path
	  * @param searchString
	  * @param replaceString
	  * @throws IOException
	  */
    private static void replaceInDirectory(Path path, String searchString, String replaceString) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path entry : stream) {
                    replaceInDirectory(entry, searchString, replaceString);
                }
            }
        } else if (Files.isRegularFile(path)) {
            replaceInFile(path, searchString, replaceString);
        }
    }
    
    /**
     * 
     * @param file
     * @param searchString
     * @param replaceString
     * @throws IOException
     */
    private static void replaceInFile(Path file, String searchString, String replaceString) throws IOException {
        String content = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
        String updatedContent = content.replace(searchString, replaceString);
        Files.write(file, updatedContent.getBytes(StandardCharsets.UTF_8));
        System.out.println("Updated file: " + file);
    }
}