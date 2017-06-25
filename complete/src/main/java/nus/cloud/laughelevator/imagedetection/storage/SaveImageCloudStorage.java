package nus.cloud.laughelevator.imagedetection.storage;

import org.springframework.stereotype.Service;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class SaveImageCloudStorage {

	public void saveImage(byte[] bytes){
		// Instantiates a client
		Storage  storage = StorageOptions.getDefaultInstance().getService();

		// The name for the new bucket
		//byte[] picByte = Base64.decodeBase64(pic);
		//GcsFileOptions instance = GcsFileOptions.getDefaultInstance();

		try{
			/*GcsFileOptions instance = new GcsFileOptions.Builder().mimeType("image/jpeg").build();
			GcsFilename fileName = new GcsFilename("xxx-app.appspot.com", "/gs/someName.jpg");
			GcsOutputChannel outputChannel;
			GcsService gcsService = GcsServiceFactory.createGcsService();
			outputChannel = gcsService.createOrReplace(fileName, instance);
			ByteBuffer a = ByteBuffer.wrap(bytes);
			outputChannel.write(a);
			outputChannel.close();*/

			// Creates the new bucket

			Bucket bucket = storage.create(BucketInfo.of("directed-hulling-3899"));
			
			//InputStream content = new ByteArrayInputStream("Hello, World!".getBytes(UTF_8));
			//Blob blob = bucket.create("test1", bytes, "image/jpeg");
			
			Page<Blob> getBlobs = bucket.list();
			for (Blob blob : getBlobs.iterateAll()) {
			  	System.out.println(blob);
			}

			System.out.printf("=========Done==========");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
