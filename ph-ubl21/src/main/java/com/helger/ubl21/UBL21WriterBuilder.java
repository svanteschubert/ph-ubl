/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.ubl21;

import javax.annotation.Nonnull;

import com.helger.commons.xml.namespace.MapBasedNamespaceContext;
import com.helger.ubl.api.builder.AbstractUBLWriterBuilder;

/**
 * A writer builder for UBL 2.1 documents.
 *
 * @author Philip Helger
 * @param <T>
 *          The UBL 2.1 implementation class to be read
 */
public class UBL21WriterBuilder <T> extends AbstractUBLWriterBuilder <T, UBL21WriterBuilder <T>>
{
  protected UBL21WriterBuilder (@Nonnull final EUBL21DocumentType eDocType)
  {
    super (eDocType);

    // Create a special namespace context for the passed document type
    final MapBasedNamespaceContext aNSContext = new MapBasedNamespaceContext ();
    aNSContext.addMappings (UBL21NamespaceContext.getInstance ());
    aNSContext.setDefaultNamespaceURI (m_aDocType.getNamespaceURI ());
    setNamespaceContext (aNSContext);
  }

  public UBL21WriterBuilder (@Nonnull final Class <T> aClass)
  {
    this (UBL21DocumentTypes.getDocumentTypeOfImplementationClass (aClass));
  }

  /**
   * Create a new writer builder.
   *
   * @param aClass
   *          The UBL class to be written. May not be <code>null</code>.
   * @return The new writer builder. Never <code>null</code>.
   */
  @Nonnull
  public static <T> UBL21WriterBuilder <T> create (@Nonnull final Class <T> aClass)
  {
    return new UBL21WriterBuilder <T> (aClass);
  }
}